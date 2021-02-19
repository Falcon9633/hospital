package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.DoctorAccDetailsBean;
import ua.com.bean.MedCardDoctorBean;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.DoctorDao;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.SpecializationDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Locale;
import ua.com.entity.Specialization;
import ua.com.util.Sorter;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

public class AdministratePatientMedicalCardsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AdministratePatientMedicalCardsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.USERS_PATIENTS_PAGE;

        long patientId = 0;
        try {
            patientId = Long.parseLong(req.getParameter("patient_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        if (!Validator.isRequestedIdValid(patientId)){
            return forward;
        }
        LOGGER.trace("requested param patient_id -> {}", patientId);
        req.setAttribute("patientId", patientId);

        forward = Path.ADMINISTRATE_PATIENT_MEDICAL_CARDS_PAGE;

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        List<MedCardDoctorBean> medCardDoctorBeans = medicalCardDao.findAllMedCardDoctorBeanByPatient(patientId);
        LOGGER.trace("found in db medCardDoctorBeans -> {}", medCardDoctorBeans);
        req.setAttribute("medCardDoctorBeans", medCardDoctorBeans);

        SpecializationDao specializationDao = DaoFactory.getSpecializationDao();
        List<Specialization> specializations = specializationDao.findAll();
        if (!specializations.isEmpty()){
            if (Locale.EN == locale){
                Sorter.sortByField(specializations, SorterConstants.ASC, Specialization::getNameEN, locale);
            }
            if (Locale.UA == locale){
                Sorter.sortByField(specializations, SorterConstants.ASC, Specialization::getNameUA, locale);
            }
        }
        LOGGER.trace("found in db specializations -> {}", specializations);

        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        List<DoctorAccDetailsBean> doctorAccDetailsBeans = doctorDao.findAllDoctorAccDetailsBeans();
        LOGGER.trace("found in db doctorAccDetailsBeans -> {}", doctorAccDetailsBeans);

        Map<Specialization, List<DoctorAccDetailsBean>> specDoctors = new LinkedHashMap<>();
        specializations.forEach(s ->
                specDoctors.put(s, doctorAccDetailsBeans
                                .stream()
                                .filter(d -> d.getSpecializationId().equals(s.getId()))
                                .collect(Collectors.toList())));
        req.setAttribute("specDoctorsMap", specDoctors);

        LOGGER.debug("execute finishes");
        return forward;
    }
}