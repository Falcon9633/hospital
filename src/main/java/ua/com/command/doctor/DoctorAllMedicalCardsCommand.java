package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class DoctorAllMedicalCardsCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        String forward = Path.DOCTOR_ALL_MEDICAL_CARDS_PAGE;

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        List<MedCardPatientBean> medCards = medicalCardDao.findAllMedCardPatientBeansTreatedByDoctor(account.getId());
        LOGGER.trace("found ind db medCards -> {}", medCards);

        Sorter.sortByLocalDateTimeField(medCards, SorterConstants.DESC, MedCardPatientBean::getCreateTime);

        req.setAttribute("medCards", medCards);
        LOGGER.trace("sorted medCards -> {}", medCards);

        LOGGER.debug("execute finishes");
        return forward;
    }
}
