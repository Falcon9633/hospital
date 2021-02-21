package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.MedCardPatientBean;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.util.Sorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DoctorCurrentMedicalCardsCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(DoctorCurrentMedicalCardsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        Account account = (Account) session.getAttribute("account");

        String forward = Path.DOCTOR_CURRENT_MEDICAL_CARDS_PAGE;

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        List<MedCardPatientBean> medCards = medicalCardDao.findAllMedCardPatientBeansByDoctor(account.getId());
        LOGGER.trace("found ind db medCards -> {}", medCards);

        if (!medCards.isEmpty()){
            if (locale == Locale.EN){
                Sorter.sortByStringField(medCards, SorterConstants.ASC, MedCardPatientBean::getPatientSurnameEN, locale);
            }
            if (locale == Locale.UA){
                Sorter.sortByStringField(medCards, SorterConstants.ASC, MedCardPatientBean::getPatientSurnameUA, locale);
            }
        }

        req.setAttribute("medCards", medCards);
        LOGGER.trace("sorted medCards -> {}", medCards);

        LOGGER.debug("execute finishes");
        return forward;
    }
}