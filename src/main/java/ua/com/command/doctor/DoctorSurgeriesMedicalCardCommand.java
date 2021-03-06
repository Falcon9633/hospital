package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.DoctorAccDetailsBean;
import ua.com.bean.SurgeryDoctorBean;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.*;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.entity.MedicalCard;
import ua.com.entity.Specialization;
import ua.com.util.Sorter;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorSurgeriesMedicalCardCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(DoctorSurgeriesMedicalCardCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        Account account = (Account) session.getAttribute("account");

        String forward = Path.REDIRECT + Path.DOCTOR_CURRENT_MEDICAL_CARDS_COMMAND;

        Long medicalCardId = null;
        Long patientId = null;
        try {
            medicalCardId = Long.parseLong(req.getParameter("medical_card_id"));
            patientId = Long.parseLong(req.getParameter("patient_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        LOGGER.trace("requested param medical_card_id -> {}", medicalCardId);
        LOGGER.trace("requested param patient_id -> {}", patientId);

        if (!Validator.isRequestedIdValid(medicalCardId) ||
                !Validator.isRequestedIdValid(patientId)) {
            return forward;
        }

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        MedicalCard medicalCard = medicalCardDao.findById(medicalCardId);
        if (medicalCard.getId() == null) {
            return forward;
        }

        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        boolean treated = doctorDao.isDoctorTreatedPatient(account.getId(), patientId);
        if (!treated) {
            return forward;
        }

        SurgeryDao surgeryDao = DaoFactory.getSurgeryDao();
        List<SurgeryDoctorBean> surgeries = surgeryDao.findAllSurgeryDoctorBeansByMedCard(medicalCardId);
        LOGGER.trace("found in db surgeries -> {}", surgeries);
        if (!surgeries.isEmpty()){
            Sorter.sortByLocalDateTimeField(surgeries, SorterConstants.ASC, SurgeryDoctorBean::getCreateTime);
        }
        req.setAttribute("surgeries", surgeries);
        req.setAttribute("medicalCardId", medicalCardId);
        req.setAttribute("patientId", patientId);
        req.setAttribute("medicalCard", medicalCard);
        req.setAttribute("doctorSideBar", true);

        SpecializationDao specializationDao = DaoFactory.getSpecializationDao();
        List<Specialization> specializations = specializationDao.findAll();

        List<DoctorAccDetailsBean> doctors = doctorDao.findAllDoctorAccDetailsBeans();

        if (Locale.EN == locale) {
            Sorter.sortByStringField(specializations, SorterConstants.ASC, Specialization::getNameEN, locale);
            Sorter.sortByStringField(doctors, SorterConstants.ASC, DoctorAccDetailsBean::getSurnameEN, locale);
        }
        if (Locale.UA == locale) {
            Sorter.sortByStringField(specializations, SorterConstants.ASC, Specialization::getNameUA, locale);
            Sorter.sortByStringField(doctors, SorterConstants.ASC, DoctorAccDetailsBean::getSurnameUA, locale);
        }
        LOGGER.trace("found in db specializations -> {}", specializations);
        LOGGER.trace("found in db doctors -> {}", doctors);

        Map<Specialization, List<DoctorAccDetailsBean>> specDoctors = new LinkedHashMap<>();
        specializations.forEach(s ->
                specDoctors.put(s, doctors
                        .stream()
                        .filter(d -> d.getSpecializationId().equals(s.getId()))
                        .collect(Collectors.toList())));
        req.setAttribute("specDoctorsMap", specDoctors);

        forward = Path.MEDICAL_CARD_SURGERIES_PAGE;

        LOGGER.debug("execute finishes");
        return forward;
    }
}
