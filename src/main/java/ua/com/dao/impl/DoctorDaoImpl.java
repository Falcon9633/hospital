package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.MySQLQuery;
import ua.com.dao.DoctorDao;
import ua.com.entity.Doctor;
import ua.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The MySQL implementation of the DoctorDao interface.
 *
 * @author Orest Dmyterko
 */
public class DoctorDaoImpl implements DoctorDao {
    private static final Logger LOGGER = LogManager.getLogger(DoctorDaoImpl.class);

    @Override
    public Doctor insertDoctor(Connection con, Doctor doctor) throws SQLException {
        LOGGER.debug("insertDoctor starts");
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.INSERT_DOCTOR);
            LOGGER.info(MySQLQuery.INSERT_ACCOUNT);
            int k = 0;
            pstmt.setLong(++k, doctor.getId());
            pstmt.setInt(++k, doctor.getSpecializationId());
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

        LOGGER.debug("insertDoctor finishes");
        return doctor;
    }
}
