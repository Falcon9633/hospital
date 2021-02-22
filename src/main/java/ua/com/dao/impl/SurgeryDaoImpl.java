package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.ProcedureDoctorBean;
import ua.com.bean.SurgeryDoctorBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.SurgeryDao;
import ua.com.entity.Procedure;
import ua.com.entity.Surgery;
import ua.com.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the SurgeryDao interface.
 *
 * @author Orest Dmyterko
 */
public class SurgeryDaoImpl implements SurgeryDao {
    private static final Logger LOGGER = LogManager.getLogger(SurgeryDaoImpl.class);

    @Override
    public Surgery findById(Long id) {
        LOGGER.debug("findByID starts");
        Surgery surgery = new Surgery();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_SURGERY_BY_ID);
            LOGGER.info(MySQLQuery.FIND_SURGERY_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                surgery = mapSurgery(rs);
            }

            DBUtil.closeResource(rs, pstmt, con);
            rs = null;
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            pstmt = null;
            con = null;
        }

        LOGGER.debug("findByID finishes");
        return surgery;
    }

    @Override
    public List<SurgeryDoctorBean> findAllSurgeryDoctorBeansByMedCard(Long medicalCardId) {
        LOGGER.debug("findAllSurgeryDoctorBeansByMedCard starts");
        List<SurgeryDoctorBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_SURGERY_DOCTOR_BEANS_BY_MEDICAL_CARD_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_SURGERY_DOCTOR_BEANS_BY_MEDICAL_CARD_ID);
            pstmt.setLong(1, medicalCardId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(mapSurgeryDoctorBean(rs));
            }

            DBUtil.closeResource(rs, pstmt, con);
            rs = null;
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            pstmt = null;
            con = null;
        }

        LOGGER.debug("findAllSurgeryDoctorBeansByMedCard finishes");
        return beans;
    }

    @Override
    public Surgery insert(Surgery surgery) {
        LOGGER.debug("insert starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.INSERT_SURGERY, Statement.RETURN_GENERATED_KEYS);
            LOGGER.info(MySQLQuery.INSERT_SURGERY);
            int k = 0;
            pstmt.setString(++k, surgery.getNameEN());
            pstmt.setString(++k, surgery.getNameUA());
            pstmt.setString(++k, surgery.getDescriptionEN());
            pstmt.setString(++k, surgery.getDescriptionUA());
            pstmt.setLong(++k, surgery.getCreatedBy());
            pstmt.setLong(++k, surgery.getServedBy());
            pstmt.setLong(++k, surgery.getMedicalCardId());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    surgery.setId(rs.getLong(1));
                }
            }

            DBUtil.closeResource(rs, pstmt, con);
            rs = null;
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            pstmt = null;
            con = null;
        }

        LOGGER.debug("insert finishes");
        return surgery;
    }

    @Override
    public boolean update(Surgery surgery) {
        LOGGER.debug("update starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_SURGERY);
            LOGGER.info(MySQLQuery.UPDATE_SURGERY);
            int k = 0;
            pstmt.setBoolean(++k, surgery.isEnd());
            pstmt.setLong(++k, surgery.getId());
            pstmt.executeUpdate();

            DBUtil.closeResource(pstmt, con);
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
            return false;
        } finally {
            try {
                DBUtil.closeResource(pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            pstmt = null;
            con = null;
        }
        LOGGER.debug("update finishes");

        return true;
    }

    private Surgery mapSurgery(ResultSet rs) throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setId(rs.getLong(MySQLFields.ID));
        surgery.setNameEN(rs.getString(MySQLFields.NAME_EN));
        surgery.setNameUA(rs.getString(MySQLFields.NAME_UA));
        surgery.setDescriptionEN(rs.getString(MySQLFields.DESCRIPTION_EN));
        surgery.setDescriptionUA(rs.getString(MySQLFields.DESCRIPTION_UA));
        surgery.setEnd(rs.getBoolean(MySQLFields.END));
        surgery.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        surgery.setCreatedBy(rs.getLong(MySQLFields.CREATED_BY));
        surgery.setServedBy(rs.getLong(MySQLFields.SERVED_BY));
        surgery.setMedicalCardId(rs.getLong(MySQLFields.MEDICAL_CARD_ID));
        return surgery;
    }

    private SurgeryDoctorBean mapSurgeryDoctorBean(ResultSet rs) throws SQLException {
        Surgery surgery = mapSurgery(rs);
        SurgeryDoctorBean bean = new SurgeryDoctorBean(surgery);
        bean.setDoctorNameEN(rs.getString(MySQLFields.MEDICAL_CARD_DOCTOR_NAME_EN));
        bean.setDoctorSurnameEN(rs.getString(MySQLFields.MEDICAL_CARD_DOCTOR_SURNAME_EN));
        bean.setDoctorNameUA(rs.getString(MySQLFields.MEDICAL_CARD_DOCTOR_NAME_UA));
        bean.setDoctorSurnameUA(rs.getString(MySQLFields.MEDICAL_CARD_DOCTOR_SURNAME_UA));
        bean.setSpecializationNameEN(rs.getString(MySQLFields.MEDICAL_CARD_SPECIALIZATION_NAME_EN));
        bean.setSpecializationNameUA(rs.getString(MySQLFields.MEDICAL_CARD_SPECIALIZATION_NAME_UA));
        bean.setServedByNameEN(rs.getString(MySQLFields.SERVED_BY_NAME_EN));
        bean.setServedBySurnameEN(rs.getString(MySQLFields.SERVED_BY_SURNAME_EN));
        bean.setServedByNameUA(rs.getString(MySQLFields.SERVED_BY_NAME_UA));
        bean.setServedBySurnameUA(rs.getString(MySQLFields.SERVED_BY_SURNAME_UA));
        return bean;
    }
}
