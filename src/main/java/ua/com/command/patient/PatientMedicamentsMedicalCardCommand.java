package ua.com.command.patient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.MedicamentDoctorBean;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.MedicamentDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.MedicalCard;
import ua.com.util.Sorter;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PatientMedicamentsMedicalCardCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(PatientMedicamentsMedicalCardCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        String forward = Path.REDIRECT + Path.PATIENT_MEDICAL_CARDS_COMMAND;

        Long medicalCardId = null;
        try {
            medicalCardId = Long.parseLong(req.getParameter("medical_card_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        LOGGER.trace("requested param medical_card_id -> {}", medicalCardId);

        if (!Validator.isRequestedIdValid(medicalCardId)) {
            return forward;
        }

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        MedicalCard medicalCard = medicalCardDao.findById(medicalCardId);
        if (medicalCard.getId() == null) {
            return forward;
        }

        if (!medicalCard.getPatientId().equals(account.getId())){
            return forward;
        }

        MedicamentDao medicamentDao = DaoFactory.getMedicamentDao();
        List<MedicamentDoctorBean> medicaments = medicamentDao.findAllMedicamentDoctorBeansByMedCard(medicalCardId);
        LOGGER.trace("found in db medicaments -> {}", medicaments);
        Sorter.sortByLocalDateTimeField(medicaments, SorterConstants.ASC, MedicamentDoctorBean::getCreateTime);

        req.setAttribute("medicaments", medicaments);
        req.setAttribute("medicalCardId", medicalCardId);
        req.setAttribute("patientSideBar", true);

        forward = Path.MEDICAL_CARD_MEDICAMENTS_PAGE;

        LOGGER.debug("execute finishes");
        return forward;
    }
}
