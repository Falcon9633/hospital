package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.SpecializationDao;
import ua.com.dao.impl.SpecializationDaoImpl;
import ua.com.entity.Role;
import ua.com.entity.Specialization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AccountRegistrationDoctorCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AccountRegistrationDoctorCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");

        SpecializationDao specializationDao = new SpecializationDaoImpl();
        List<Specialization> specializations = specializationDao.findAll();

        LOGGER.trace("found in DB specializations -> {}", specializations);
        req.setAttribute("specializations", specializations);

        LOGGER.debug("execute finishes");
        return Path.ACCOUNT_REGISTRATION_DOCTOR_PAGE;
    }
}
