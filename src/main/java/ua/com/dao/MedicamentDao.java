package ua.com.dao;

import ua.com.bean.MedicamentDoctorBean;
import ua.com.entity.Medicament;

import java.util.List;

/**
 * Data access object for the Medicament entity.
 *
 * @author Orest Dmyterko
 */
public interface MedicamentDao {
    Medicament findById(Long id);

    List<MedicamentDoctorBean> findAllMedicamentDoctorBeansByMedCard(Long medicalCardId);

    Medicament insert(Medicament medicament);


    boolean update(Medicament medicament);
}
