package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.DoctorAccDetailsBean;
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
}
