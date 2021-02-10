package ua.com.dao;

import ua.com.entity.Patient;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data access object for the Patient entity.
 *
 * @author Orest Dmyterko
 */
public interface PatientDao {
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
}
