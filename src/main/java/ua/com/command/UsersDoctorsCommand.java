package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.DoctorAccountBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.DoctorDao;
import ua.com.dao.SpecializationDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Locale;
import ua.com.entity.Specialization;
import ua.com.util.Sorter;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UsersDoctorsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UsersDoctorsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.USERS_DOCTORS_PAGE;
        List<DoctorAccountBean> doctorAccountBeans =
                (List<DoctorAccountBean>) session.getAttribute("doctorAccountBeans");


        if (doctorAccountBeans == null){
            DoctorDao doctorDao = DaoFactory.getDoctorDao();
            doctorAccountBeans = doctorDao.findAllDoctorAccountBeans();
            LOGGER.trace("found in db doctorAccountBeans -> {}", doctorAccountBeans);
        }

        String sortBy = req.getParameter("sortBy");
        LOGGER.trace("requested param sortBy -> {}", sortBy);
        sortBy = Validator.checkUsersDoctorSortBy(sortBy);

        String sortDir = req.getParameter("sortDir");
        LOGGER.trace("requested param sortDir -> {}", sortDir);
        sortDir = Validator.checkSortingDirection(sortDir);
        req.setAttribute("sortDir", SorterConstants.ASC.equals(sortDir) ? SorterConstants.DESC : SorterConstants.ASC);

        //sort if not empty
        if (!doctorAccountBeans.isEmpty()){
            //sort by surname
            if (SorterConstants.SURNAME.equals(sortBy)){
                if (locale == Locale.EN) {
                    Sorter.sortByField(doctorAccountBeans, sortDir, DoctorAccountBean::getSurnameEN, locale);
                }
                if (locale == Locale.UA) {
                    Sorter.sortByField(doctorAccountBeans, sortDir, DoctorAccountBean::getSurnameUA, locale);
                }
            }
            //sort by specialization
            if (SorterConstants.SPECIALIZATION.equals(sortBy)){
                if (locale == Locale.EN){
                    Sorter.sortByField(doctorAccountBeans, sortDir, DoctorAccountBean::getSpecializationNameEN, locale);
                }
                if (locale == Locale.UA){
                    Sorter.sortByField(doctorAccountBeans, sortDir, DoctorAccountBean::getSpecializationNameUA, locale);
                }
            }
            //sort by patients count
            if (SorterConstants.PATIENT_COUNT.equals(sortBy)){
                Sorter.sortByField(doctorAccountBeans, sortDir, DoctorAccountBean::getPatientCount);
            }
        }

        session.setAttribute("doctorAccountBeans", doctorAccountBeans);
        LOGGER.trace("sorted doctorAccountBeans -> {}", doctorAccountBeans);

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
        return forward;
    }
}
