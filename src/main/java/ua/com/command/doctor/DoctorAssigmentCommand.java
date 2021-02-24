package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.EmployeeAssignmentBean;
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
import java.util.ArrayList;
import java.util.List;

public class DoctorAssigmentCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(DoctorAssigmentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        String forward = Path.EMPLOYEE_ASSIGNMENT_PAGE;

        MedicamentDao medicamentDao = DaoFactory.getMedicamentDao();
        List<EmployeeMedicamentBean> medicaments = medicamentDao.findAllEmployeeMedicamentBeansByEmp(account.getId());
        LOGGER.trace("found in db medicaments -> {}", medicaments);

        SurgeryDao surgeryDao = DaoFactory.getSurgeryDao();
        List<EmployeeSurgeryBean> surgeries = surgeryDao.findAllEmployeeSurgeryBeansByEmp(account.getId());
        LOGGER.trace("found in db surgeries -> {}", surgeries);

        ProcedureDao procedureDao = DaoFactory.getProcedureDao();
        List<EmployeeProcedureBean> procedures = procedureDao.findAllEmployeeProcedureBeansByEmp(account.getId());
        LOGGER.trace("found in db procedures -> {}", procedures);

        if (!medicaments.isEmpty()){
            Sorter.sortByLocalDateTimeField(medicaments, SorterConstants.ASC, EmployeeMedicamentBean::getCreateTime);
        }
        if (!surgeries.isEmpty()){
            Sorter.sortByLocalDateTimeField(surgeries, SorterConstants.ASC, EmployeeSurgeryBean::getCreateTime);
        }
        if (!procedures.isEmpty()){
            Sorter.sortByLocalDateTimeField(procedures, SorterConstants.ASC, EmployeeProcedureBean::getCreateTime);
        }

        req.setAttribute("medicaments", medicaments);
        req.setAttribute("surgeries", surgeries);
        req.setAttribute("procedures", procedures);

        return forward;
    }
}
