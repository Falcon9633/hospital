package ua.com.command.patient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.MedCardDoctorBean;
import ua.com.bean.MedCardPatientBean;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.util.Sorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PatientMedicalCardsCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(PatientMedicalCardsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        List<MedCardDoctorBean> medCards = medicalCardDao.findAllMedCardDoctorBeanByPatient(account.getId());
        LOGGER.trace("found ind db medCards -> {}", medCards);

        Sorter.sortMedCardDoctorBeanByDischAndCreateDate(medCards);

        req.setAttribute("medCards", medCards);

        LOGGER.debug("execute finishes");
        return Path.PATIENT_MEDICAL_CARDS_PAGE;
    }
}
