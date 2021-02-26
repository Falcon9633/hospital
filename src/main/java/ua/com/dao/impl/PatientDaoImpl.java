package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.PatientAccountBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.PatientDao;
import ua.com.entity.Patient;
import ua.com.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the PatientDao interface.
 *
 * @author Orest Dmyterko
 */
public class PatientDaoImpl implements PatientDao {
    private static final Logger LOGGER = LogManager.getLogger(PatientDaoImpl.class);

    @Override
    public Patient findById(Long id, Connection con) throws SQLException {
        LOGGER.debug("findByID starts");
        Patient patient = new Patient();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.FIND_PATIENT_BY_ID);
            LOGGER.info(MySQLQuery.FIND_PATIENT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                patient = ObjectMapper.mapPatient(rs);
            }

            DBUtil.closeResource(rs, pstmt);
            rs = null;
            pstmt = null;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new SQLException(e.getCause());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            pstmt = null;
        }

        LOGGER.debug("findByID finishes");
        return patient;
    }

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
            LOGGER.error(e.getMessage(), e.getCause());
            throw new SQLException(e.getCause());
        } finally {
            try {
                DBUtil.closeResource(pstmt);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            pstmt = null;
        }

        LOGGER.debug("insertPatient finishes");
        return patient;
    }

    @Override
    public List<PatientAccountBean> findAllPatientAccountBeans(int currentPage, int recordsPerPage, String sortBy, String sortDir) {
        LOGGER.debug("findAllPatientAccountBeans starts");
        List<PatientAccountBean> beans = new ArrayList<>();
        long start = (long) currentPage * recordsPerPage - recordsPerPage;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(String.format(MySQLQuery.FIND_ALL_PATIENT_ACCOUNT_BEANS, sortBy, sortDir));
            LOGGER.info(MySQLQuery.FIND_ALL_PATIENT_ACCOUNT_BEANS);
            int k = 0;
            pstmt.setLong(++k, start);
            pstmt.setInt(++k, recordsPerPage);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(ObjectMapper.mapPatientAccountBean(rs));
            }

            DBUtil.closeResource(rs, pstmt, con);
            rs = null;
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            pstmt = null;
            con = null;
        }

        LOGGER.debug("findAllPatientAccountBeans finishes");
        return beans;
    }

    @Override
    public long getNumberOfRows() {
        LOGGER.debug("getNumberOfRows starts");
        long numberOfRows = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MySQLQuery.GET_NUMBER_OF_ROWS_PATIENT);
            LOGGER.info(MySQLQuery.GET_NUMBER_OF_ROWS_PATIENT);

            if (rs.next()) {
                numberOfRows = rs.getLong(MySQLFields.NUMBER);
            }
            DBUtil.closeResource(rs, stmt, con);
            rs = null;
            stmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            stmt = null;
            con = null;
        }

        LOGGER.debug("getNumberOfRows finishes");
        return numberOfRows;
    }

    @Override
    public void update(Patient patient, Connection con) throws SQLException {
        LOGGER.debug("update starts");
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_PATIENT);
            LOGGER.info(MySQLQuery.UPDATE_PATIENT);
            int k = 0;
            pstmt.setDate(++k, Date.valueOf(patient.getBirthday()));
            pstmt.setLong(++k, patient.getId());
            pstmt.executeUpdate();

            DBUtil.closeResource(pstmt);
            pstmt = null;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new SQLException(e.getCause());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(pstmt);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            pstmt = null;
        }
        LOGGER.debug("update finishes");
    }
}
