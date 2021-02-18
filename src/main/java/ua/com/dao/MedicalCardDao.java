package ua.com.dao;

import ua.com.bean.MedCardDoctorBean;
import ua.com.entity.MedicalCard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the MedicalCard entity.
 *
 * @author Orest Dmyterko
 */
public interface MedicalCardDao {
    MedicalCard findById(Long id);

    MedicalCard insert(MedicalCard medicalCard);

    MedicalCard insert(MedicalCard medicalCard, Connection con) throws SQLException;

    List<MedCardDoctorBean> findAllMedCardDoctorBeanByPatient(Long patientId);

    boolean update(MedicalCard medicalCard);
}
