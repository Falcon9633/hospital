package ua.com.dao;

import ua.com.bean.PatientAccountBean;
import ua.com.entity.Patient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the Patient entity.
 *
 * @author Orest Dmyterko
 */
public interface PatientDao {
    Patient findById(Long id, Connection con) throws SQLException;

    /**
     * Inserts given patient to the database.
     *
     * @param con     Connection to the db
     * @param patient to insert into db
     * @return inserted patient
     * @throws SQLException if the given connection closed
     * @see Connection
     * @see java.sql.PreparedStatement
     */
    Patient insertPatient(Connection con, Patient patient) throws SQLException;

    List<PatientAccountBean> findAllPatientAccountBeans(int currentPage, int recordsPerPage, String sortBy, String sortDir);

    long getNumberOfRows();

    void update(Patient patient, Connection con) throws SQLException;
}
