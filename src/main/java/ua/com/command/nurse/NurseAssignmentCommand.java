package ua.com.command.nurse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.EmployeeMedicamentBean;
import ua.com.bean.EmployeeProcedureBean;
import ua.com.bean.EmployeeSurgeryBean;
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

public class NurseAssignmentCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(NurseAssignmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        String forward = Path.EMPLOYEE_ASSIGNMENT_PAGE;

        MedicamentDao medicamentDao = DaoFactory.getMedicamentDao();
        List<EmployeeMedicamentBean> medicaments = medicamentDao.findAllEmployeeMedicamentBeansByEmp(account.getId());
        LOGGER.trace("found in db medicaments -> {}", medicaments);

        ProcedureDao procedureDao = DaoFactory.getProcedureDao();
        List<EmployeeProcedureBean> procedures = procedureDao.findAllEmployeeProcedureBeansByEmp(account.getId());
        LOGGER.trace("found in db procedures -> {}", procedures);

        if (!medicaments.isEmpty()){
            Sorter.sortByLocalDateTimeField(medicaments, SorterConstants.ASC, EmployeeMedicamentBean::getCreateTime);
        }
        if (!procedures.isEmpty()){
            Sorter.sortByLocalDateTimeField(procedures, SorterConstants.ASC, EmployeeProcedureBean::getCreateTime);
        }

        req.setAttribute("medicaments", medicaments);
        req.setAttribute("procedures", procedures);

        return forward;
    }
}
