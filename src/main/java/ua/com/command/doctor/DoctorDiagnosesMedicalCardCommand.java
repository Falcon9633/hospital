package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.DiagnosisDoctorBean;
import ua.com.command.Command;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.DiagnosisDao;
import ua.com.dao.DoctorDao;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.entity.MedicalCard;
import ua.com.util.Sorter;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DoctorDiagnosesMedicalCardCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DoctorDiagnosesMedicalCardCommand.class);

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
        if (medicalCard.getId() == null){
            return forward;
        }

        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        boolean treated = doctorDao.isDoctorTreatedPatient(account.getId(), patientId);
        if (!treated){
            return forward;
        }

        DiagnosisDao diagnosisDao = DaoFactory.getDiagnosisDao();
        List<DiagnosisDoctorBean> diagnoses = diagnosisDao.findAllDiagnosisDoctorBeansByMedCard(medicalCardId);
        LOGGER.trace("found in db diagnoses -> {}", diagnoses);
        if (!diagnoses.isEmpty()){
            Sorter.sortByLocalDateTimeField(diagnoses, SorterConstants.ASC, DiagnosisDoctorBean::getCreateTime);
        }
        req.setAttribute("diagnoses", diagnoses);
        req.setAttribute("medicalCardId", medicalCardId);
        req.setAttribute("patientId", patientId);

        forward = Path.MEDICAL_CARD_DIAGNOSES_PAGE;

        LOGGER.debug("execute finishes");
        return forward;
    }
}
