package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.MedCardPatientBean;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.DoctorDao;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.util.Sorter;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DoctorPatientHistoryMedicalCards implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(DoctorPatientHistoryMedicalCards.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        String forward = Path.REDIRECT + Path.DOCTOR_CURRENT_MEDICAL_CARDS_COMMAND;

        Long patientId = null;
        try {
            patientId = Long.parseLong(req.getParameter("patient_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        LOGGER.trace("requested param patient_id -> {}", patientId);

        if (!Validator.isRequestedIdValid(patientId)) {
            return forward;
        }

        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        boolean treated = doctorDao.isDoctorTreatedPatient(account.getId(), patientId);
        if (!treated){
            return forward;
        }

        forward = Path.DOCTOR_PATIENT_HISTORY_MEDICAL_CARDS_PAGE;

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        List<MedCardPatientBean> medCards = medicalCardDao.findAllMedCardPatientBeansByPatient(patientId);
        LOGGER.trace("found ind db medCards -> {}", medCards);

        if (!medCards.isEmpty()){
            Sorter.sortByLocalDateTimeField(medCards, SorterConstants.DESC, MedCardPatientBean::getCreateTime);
        }

        req.setAttribute("medCards", medCards);
        LOGGER.trace("sorted medCards -> {}", medCards);

        LOGGER.debug("execute finishes");
        return forward;
    }
}
