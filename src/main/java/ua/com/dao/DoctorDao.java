package ua.com.dao;

import ua.com.bean.DoctorAccDetailsBean;
import ua.com.bean.DoctorAccountBean;
import ua.com.entity.Doctor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the Doctor entity.
 *
 * @author Orest Dmyterko
 */
public interface DoctorDao {
    Doctor findById (Long id, Connection con) throws SQLException;
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

    void update(Doctor doctor, Connection con) throws SQLException;

    List<DoctorAccDetailsBean> findAllDoctorAccDetailsBeans();

    List<DoctorAccountBean> findAllDoctorAccountBeans();

    boolean isDoctorTreatedPatient(Long doctorId, Long patientId);
}
