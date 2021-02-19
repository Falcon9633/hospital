package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.PatientAccountBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.PatientDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Locale;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UsersPatientsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UsersPatientsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.USERS_PATIENTS_PAGE;

        int recordsPerPage = 15;
        int currentPage;
        try {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
            LOGGER.trace("requested param currentPage -> {}", currentPage);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            currentPage = 1;
        }

        PatientDao patientDao = DaoFactory.getPatientDao();

        long rows = patientDao.getNumberOfRows();
        LOGGER.trace("found in db rows -> {}", rows);

        long numOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            numOfPages++;
        }

        req.setAttribute("numOfPages", numOfPages);
        req.setAttribute("currentPage", currentPage);

        String sortBy = req.getParameter("sortBy");
        LOGGER.trace("requested param sortBy -> {}", sortBy);
        sortBy = Validator.checkUsersPatientSortBy(sortBy);
        req.setAttribute("sortBy", sortBy);

        if (sortBy.equals(SorterConstants.SURNAME)) {
            if (locale == Locale.EN) sortBy = MySQLFields.ACCOUNT_DETAILS_SURNAME_EN;
            if (locale == Locale.UA) sortBy = MySQLFields.ACCOUNT_DETAILS_SURNAME_UA;
        }

        String sortDir = req.getParameter("sortDir");
        LOGGER.trace("requested param sortDir -> {}", sortDir);
        sortDir = Validator.checkSortingDirection(sortDir);
        req.setAttribute("sortDir", SorterConstants.ASC.equals(sortDir) ? SorterConstants.DESC : SorterConstants.ASC);
        req.setAttribute("sortDirPagination", sortDir);

        List<PatientAccountBean> patientAccountBeans =
                patientDao.findAllPatientAccountBeans(currentPage, recordsPerPage, sortBy, sortDir);
        req.setAttribute("patientAccountBeans", patientAccountBeans);
        LOGGER.trace("patientAccountBeans -> {}", patientAccountBeans);

        LOGGER.debug("execute finishes");
        return forward;
    }
}
