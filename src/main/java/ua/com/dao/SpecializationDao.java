package ua.com.dao;

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
}
