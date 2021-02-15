package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.SpecializationAccountDetailsBean;
import ua.com.constant.Path;
import ua.com.dao.SpecializationDao;
import ua.com.dao.impl.SpecializationDaoImpl;
import ua.com.entity.Locale;
import ua.com.util.Sorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DoctorSpecializationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DoctorSpecializationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");
        List<SpecializationAccountDetailsBean> specAccDetailList =
                (List<SpecializationAccountDetailsBean>) session.getAttribute("specAccDetailList");

        if (specAccDetailList == null){
            SpecializationDao specializationDao = new SpecializationDaoImpl();
            specAccDetailList = specializationDao.findAllSpecAccDetailsBeans();
            LOGGER.trace("found in db specAccDetailList -> {}", specAccDetailList);
        }

        String sortBy = req.getParameter("sortBy");
        LOGGER.trace("requested param sortBy -> {}", sortBy);
        if (!specAccDetailList.isEmpty() && (sortBy == null || "name".equals(sortBy))) {
            String sortDirName = req.getParameter("sortDirName");
            LOGGER.trace("requested param sortDirName -> {}", sortDirName);
            Sorter.sortSpecAccDetListByName(specAccDetailList, locale, sortDirName, req);
        }


        session.setAttribute("specAccDetailList", specAccDetailList);
        LOGGER.trace("sorted specAccDetailList -> {}", specAccDetailList);

        LOGGER.debug("execute finishes");
        return Path.DOCTOR_SPECIALIZATION_PAGE;
    }
}
