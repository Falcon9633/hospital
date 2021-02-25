package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.DoctorAccDetailsBean;
import ua.com.bean.DoctorAccountBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.DoctorDao;
import ua.com.entity.Doctor;
import ua.com.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the DoctorDao interface.
 *
 * @author Orest Dmyterko
 */
public class DoctorDaoImpl implements DoctorDao {
    private static final Logger LOGGER = LogManager.getLogger(DoctorDaoImpl.class);

    @Override
    public Doctor findById(Long id, Connection con) throws SQLException {
        LOGGER.debug("findById starts");
        Doctor doctor = new Doctor();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.FIND_DOCTOR_BY_ID);
            LOGGER.info(MySQLQuery.FIND_DOCTOR_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()){
                doctor = mapDoctor(rs);
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

        LOGGER.debug("findById finishes");
        return doctor;
    }

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

    @Override
    public void update(Doctor doctor, Connection con) throws SQLException {
        LOGGER.debug("update starts");
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_DOCTOR);
            LOGGER.info(MySQLQuery.UPDATE_DOCTOR);
            int k = 0;
            pstmt.setInt(++k, doctor.getSpecializationId());
            pstmt.setLong(++k, doctor.getId());
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

    @Override
    public List<DoctorAccDetailsBean> findAllDoctorAccDetailsBeans() {
        LOGGER.debug("findAllDoctorAccDetailsBeans starts");
        List<DoctorAccDetailsBean> beans = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MySQLQuery.FIND_ALL_DOCTOR_ACCOUNT_DETAILS_BEANS);
            LOGGER.info(MySQLQuery.FIND_ALL_DOCTOR_ACCOUNT_DETAILS_BEANS);

            while (rs.next()){
                beans.add(mapDoctorAccDetailsBean(rs));
            }

            DBUtil.closeResource(rs, stmt, con);
            rs = null;
            stmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, stmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            stmt = null;
            con = null;
        }

        LOGGER.debug("findAllDoctorAccDetailsBeans finishes");
        return beans;
    }

    @Override
    public List<DoctorAccountBean> findAllDoctorAccountBeans() {
        LOGGER.debug("findAllDoctorAccountBeans starts");
        List<DoctorAccountBean> beans = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MySQLQuery.FIND_ALL_DOCTOR_ACCOUNT_BEANS);
            LOGGER.info(MySQLQuery.FIND_ALL_DOCTOR_ACCOUNT_BEANS);

            while (rs.next()){
                beans.add(mapDoctorAccountBean(rs));
            }

            DBUtil.closeResource(rs, stmt, con);
            rs = null;
            stmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, stmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            stmt = null;
            con = null;
        }

        LOGGER.debug("findAllDoctorAccountBeans finishes");
        return beans;
    }

    @Override
    public boolean isDoctorTreatedPatient(Long doctorId, Long patientId) {
        LOGGER.debug("isDoctorTreatedPatient starts");
        boolean isTreated = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.IS_DOCTOR_TREATED_PATIENT);
            LOGGER.info(MySQLQuery.IS_DOCTOR_TREATED_PATIENT);
            int k = 0;
            pstmt.setLong(++k, doctorId);
            pstmt.setLong(++k, patientId);
            isTreated = pstmt.execute();

            DBUtil.closeResource(pstmt, con);
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            pstmt = null;
            con = null;
        }
        return isTreated;
    }

    private Doctor mapDoctor(ResultSet rs) throws SQLException{
        LOGGER.debug("mapDoctor starts");
        Doctor doctor = new Doctor();
        doctor.setId(rs.getLong(MySQLFields.ID));
        doctor.setSpecializationId(rs.getInt(MySQLFields.DOCTOR_SPECIALIZATION_ID));
        return doctor;
    }

    private DoctorAccDetailsBean mapDoctorAccDetailsBean(ResultSet rs) throws SQLException {
        LOGGER.debug("mapDoctorAccDetailsBean starts");
        DoctorAccDetailsBean bean = new DoctorAccDetailsBean();
        bean.setId(rs.getLong(MySQLFields.ID));
        bean.setSpecializationId(rs.getInt(MySQLFields.DOCTOR_SPECIALIZATION_ID));
        bean.setNameEN(rs.getString(MySQLFields.NAME_EN));
        bean.setSurnameEN(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_EN));
        bean.setNameUA(rs.getString(MySQLFields.NAME_UA));
        bean.setSurnameUA(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_UA));
        return bean;
    }

    private DoctorAccountBean mapDoctorAccountBean(ResultSet rs) throws SQLException{
        LOGGER.debug("mapDoctorAccountBean starts");
        DoctorAccountBean bean = new DoctorAccountBean(mapDoctorAccDetailsBean(rs));
        bean.setEmail(rs.getString(MySQLFields.ACCOUNT_EMAIL));
        bean.setLocked(rs.getBoolean(MySQLFields.ACCOUNT_LOCKED));
        bean.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        bean.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        bean.setUpdatedByNameEN(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_EN));
        bean.setUpdatedBySurnameEN(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_EN));
        bean.setUpdatedByNameUA(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_UA));
        bean.setUpdatedBySurnameUA(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_UA));
        bean.setPatientCount(rs.getInt(MySQLFields.DOCTOR_PATIENT_COUNT));
        bean.setSpecializationNameEN(rs.getString(MySQLFields.SPECIALIZATION_NAME_EN));
        bean.setSpecializationNameUA(rs.getString(MySQLFields.SPECIALIZATION_NAME_UA));
        return bean;
    }
}
