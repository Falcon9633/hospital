package ua.com.dao;

import ua.com.bean.EmployeeProcedureBean;
import ua.com.bean.ProcedureDoctorBean;
import ua.com.entity.Medicament;
import ua.com.entity.Procedure;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the Procedure entity.
 *
 * @author Orest Dmyterko
 */
public interface ProcedureDao {
    Procedure findById(Long id);

    List<Procedure> findAllNotEndByMedicalCard(Long medicalCardId, Connection con) throws SQLException;

    List<ProcedureDoctorBean> findAllProcedureDoctorBeansByMedCard(Long medicalCardId);

    List<EmployeeProcedureBean> findAllEmployeeProcedureBeansByEmp(Long id);

    Procedure insert(Procedure procedure);

    boolean update(Procedure procedure);

    void updateAllToEnd(List<Procedure> procedures, Connection con) throws SQLException;
}
