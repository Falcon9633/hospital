package ua.com.dao;

import ua.com.bean.SpecializationAccountDetailsBean;
import ua.com.entity.Specialization;

import java.util.List;

/**
 * Data access object for the Specialization entity.
 *
 * @author Orest Dmyterko
 */
public interface SpecializationDao {
    /**
     * Returns all specializations.
     *
     * @return List Specialization entity
     */
    List<Specialization> findAll();

    List<SpecializationAccountDetailsBean> findAllSpecAccDetailsBeans();

    Specialization findById(Integer id);

    List<Specialization> findByName(String nameEN, String nameUA);

    Specialization insert(Specialization specialization);

    boolean update(Specialization specialization);
}
