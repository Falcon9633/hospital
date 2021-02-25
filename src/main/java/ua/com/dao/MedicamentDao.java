package ua.com.dao;

import ua.com.bean.EmployeeAssignmentBean;
import ua.com.bean.EmployeeMedicamentBean;
import ua.com.bean.MedicamentDoctorBean;
import ua.com.bean.PatientMedicamentBean;
import ua.com.entity.Medicament;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the Medicament entity.
 *
 * @author Orest Dmyterko
 */
public interface MedicamentDao {
    Medicament findById(Long id);

    List<Medicament> findAllNotEndByMedicalCard(Long medicalCardId, Connection con) throws SQLException;

    List<MedicamentDoctorBean> findAllMedicamentDoctorBeansByMedCard(Long medicalCardId);

    List<EmployeeMedicamentBean> findAllEmployeeMedicamentBeansByEmp(Long empId);

    List<PatientMedicamentBean> findAllPatientMedicamentBeansByPatient(Long patientId);

    Medicament insert(Medicament medicament);

    boolean update(Medicament medicament);

    void updateAllToEnd(List<Medicament> medicaments, Connection con) throws SQLException;
}
