package ua.com.command.patient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.*;
import ua.com.constant.Path;
import ua.com.constant.SorterConstants;
import ua.com.dao.MedicamentDao;
import ua.com.dao.ProcedureDao;
import ua.com.dao.SurgeryDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.util.Sorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PatientAssignmentCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(PatientAssignmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        String forward = Path.PATIENT_ASSIGNMENT_PAGE;

        MedicamentDao medicamentDao = DaoFactory.getMedicamentDao();
        List<PatientMedicamentBean> medicaments = medicamentDao.findAllPatientMedicamentBeansByPatient(account.getId());
        LOGGER.trace("found in db medicaments -> {}", medicaments);

        ProcedureDao procedureDao = DaoFactory.getProcedureDao();
        List<PatientProcedureBean> procedures = procedureDao.findAllPatientProcedureBeansByPatient(account.getId());
        LOGGER.trace("found in db procedures -> {}", procedures);

        SurgeryDao surgeryDao = DaoFactory.getSurgeryDao();
        List<PatientSurgeryBean> surgeries = surgeryDao.findAllPatientSurgeryBeansByPatient(account.getId());
        LOGGER.trace("found in db surgeries -> {}", surgeries);

        Sorter.sortByLocalDateTimeField(medicaments, SorterConstants.ASC, PatientMedicamentBean::getCreateTime);
        Sorter.sortByLocalDateTimeField(procedures, SorterConstants.ASC, PatientProcedureBean::getCreateTime);
        Sorter.sortByLocalDateTimeField(surgeries, SorterConstants.ASC, PatientSurgeryBean::getCreateTime);

        req.setAttribute("medicaments", medicaments);
        req.setAttribute("procedures", procedures);
        req.setAttribute("surgeries", surgeries);

        return forward;
    }
}
