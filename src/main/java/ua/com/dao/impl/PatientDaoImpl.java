package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.MySQLQuery;
import ua.com.dao.PatientDao;
import ua.com.entity.Patient;
import ua.com.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The MySQL implementation of the PatientDao interface.
 *
 * @author Orest Dmyterko
 */
public class PatientDaoImpl implements PatientDao {
    private static final Logger LOGGER = LogManager.getLogger(PatientDaoImpl.class);

    @Override
    public Patient insertPatient(Connection con, Patient patient) throws SQLException {
        LOGGER.debug("insertPatient starts");
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.INSERT_PATIENT);
            LOGGER.info(MySQLQuery.INSERT_PATIENT);
            int k = 0;
            pstmt.setLong(++k, patient.getId());
            pstmt.setDate(++k, Date.valueOf(patient.getBirthday()));
            pstmt.executeUpdate();

            DBUtil.closeResource(pstmt);
            pstmt = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new SQLException(e.getCause());
        } finally {
            try {
                DBUtil.closeResource(pstmt);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            pstmt = null;
        }

        LOGGER.debug("insertPatient finishes");
        return patient;
    }
}
