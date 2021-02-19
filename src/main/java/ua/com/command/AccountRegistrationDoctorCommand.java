package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.SpecializationDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.dao.impl.SpecializationDaoImpl;
import ua.com.entity.Locale;
import ua.com.entity.Role;
import ua.com.entity.Specialization;
import ua.com.util.Sorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AccountRegistrationDoctorCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AccountRegistrationDoctorCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute("locale");

        SpecializationDao specializationDao = DaoFactory.getSpecializationDao();
        List<Specialization> specializations = specializationDao.findAll();
        LOGGER.trace("found in DB specializations -> {}", specializations);

        if (Locale.EN == locale){
            Sorter.sortByField(specializations, SorterConstants.ASC, Specialization::getNameEN, locale);
        }
        if (Locale.UA == locale){
            Sorter.sortByField(specializations, SorterConstants.ASC, Specialization::getNameEN, locale);
        }
        LOGGER.trace("sorted specializations -> {}", specializations);

        req.setAttribute("specializations", specializations);

        LOGGER.debug("execute finishes");
        return Path.ACCOUNT_REGISTRATION_DOCTOR_PAGE;
    }
}
