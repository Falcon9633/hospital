package ua.com.dao;

import ua.com.bean.SurgeryDoctorBean;
import ua.com.entity.Surgery;

import java.util.List;

/**
 * Data access object for the Surgery entity.
 *
 * @author Orest Dmyterko
 */
public interface SurgeryDao {
    Surgery findById(Long id);

    List<SurgeryDoctorBean> findAllSurgeryDoctorBeansByMedCard(Long medicalCardId);

    Surgery insert(Surgery surgery);

    boolean update(Surgery surgery);
}
