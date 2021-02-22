package ua.com.dao;

import ua.com.bean.ProcedureDoctorBean;
import ua.com.entity.Procedure;

import java.util.List;

/**
 * Data access object for the Procedure entity.
 *
 * @author Orest Dmyterko
 */
public interface ProcedureDao {
    Procedure findById(Long id);

    List<ProcedureDoctorBean> findAllProcedureDoctorBeansByMedCard(Long medicalCardId);

    Procedure insert(Procedure procedure);

    boolean update(Procedure procedure);
}
