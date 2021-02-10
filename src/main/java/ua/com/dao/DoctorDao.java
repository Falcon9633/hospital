package ua.com.dao;

import ua.com.entity.Doctor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data access object for the Doctor entity.
 *
 * @author Orest Dmyterko
 */
public interface DoctorDao {
    /**
     * Inserts given doctor to the database.
     *
     * @param con    Connection to the db
     * @param doctor to insert into db
     * @return inserted doctor
     * @throws SQLException if the given connection closed
     * @see Connection
     * @see java.sql.PreparedStatement
     */
    Doctor insertDoctor(Connection con, Doctor doctor) throws SQLException;
}
