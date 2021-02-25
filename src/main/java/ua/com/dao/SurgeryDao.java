package ua.com.dao;

import ua.com.bean.EmployeeSurgeryBean;
import ua.com.bean.PatientSurgeryBean;
import ua.com.bean.SurgeryDoctorBean;
import ua.com.entity.Surgery;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the Surgery entity.
 *
 * @author Orest Dmyterko
 */
public interface SurgeryDao {
    Surgery findById(Long id);

    List<Surgery> findAllNotEndByMedicalCard(Long medicalCardId, Connection con) throws SQLException;

    List<SurgeryDoctorBean> findAllSurgeryDoctorBeansByMedCard(Long medicalCardId);

    List<EmployeeSurgeryBean> findAllEmployeeSurgeryBeansByEmp(Long empId);

    List<PatientSurgeryBean> findAllPatientSurgeryBeansByPatient(Long patientId);

    Surgery insert(Surgery surgery);

    boolean update(Surgery surgery);

    void updateAllToEnd(List<Surgery> surgeries, Connection con) throws SQLException;
}
