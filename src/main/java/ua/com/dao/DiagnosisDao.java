package ua.com.dao;

import ua.com.bean.DiagnosisDoctorBean;
import ua.com.entity.Diagnosis;
import ua.com.entity.MedicalCard;

import java.util.List;

/**
 * Data access object for the Diagnosis entity.
 *
 * @author Orest Dmyterko
 */
public interface DiagnosisDao {
    Diagnosis insert(Diagnosis diagnosis);

    List<DiagnosisDoctorBean> findAllDiagnosisDoctorBeansByMedCard(Long medicalCardId);
}
