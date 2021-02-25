package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.DiagnosisDoctorBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.DiagnosisDao;
import ua.com.entity.Diagnosis;
import ua.com.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the DiagnosisDao interface.
 *
 * @author Orest Dmyterko
 */
public class DiagnosisDaoImpl implements DiagnosisDao {
    private static final Logger LOGGER = LogManager.getLogger(DoctorDaoImpl.class);

    @Override
    public Diagnosis insert(Diagnosis diagnosis) {
        LOGGER.debug("insert starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.INSERT_DIAGNOSIS, Statement.RETURN_GENERATED_KEYS);
            LOGGER.info(MySQLQuery.INSERT_DIAGNOSIS);
            int k = 0;
            pstmt.setString(++k, diagnosis.getNameEN());
            pstmt.setString(++k, diagnosis.getNameUA());
            pstmt.setString(++k, diagnosis.getDescriptionEN());
            pstmt.setString(++k, diagnosis.getDescriptionUA());
            pstmt.setLong(++k, diagnosis.getCreatedBy());
            pstmt.setLong(++k, diagnosis.getMedicalCardId());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    diagnosis.setId(rs.getLong(1));
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
        return diagnosis;
    }

    @Override
    public List<DiagnosisDoctorBean> findAllDiagnosisDoctorBeansByMedCard(Long medicalCardId) {
        LOGGER.debug("findAllDiagnosisDoctorBeansByMedCard starts");
        List<DiagnosisDoctorBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_DIAGNOSIS_DOCTOR_BEANS_BY_MEDICAL_CARD_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_DIAGNOSIS_DOCTOR_BEANS_BY_MEDICAL_CARD_ID);
            pstmt.setLong(1, medicalCardId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(mapDiagnosisDoctorBean(rs));
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

        LOGGER.debug("findAllDiagnosisDoctorBeansByMedCard finishes");
        return beans;
    }

    private DiagnosisDoctorBean mapDiagnosisDoctorBean(ResultSet rs) throws SQLException {
        DiagnosisDoctorBean bean = new DiagnosisDoctorBean();
        bean.setId(rs.getLong(MySQLFields.ID));
        bean.setNameEN(rs.getString(MySQLFields.NAME_EN));
        bean.setNameUA(rs.getString(MySQLFields.NAME_UA));
        bean.setDescriptionEN(rs.getString(MySQLFields.DESCRIPTION_EN));
        bean.setDescriptionUA(rs.getString(MySQLFields.DESCRIPTION_UA));
        bean.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        bean.setCreatedBy(rs.getLong(MySQLFields.CREATED_BY));
        bean.setMedicalCardId(rs.getLong(MySQLFields.MEDICAL_CARD_ID));
        bean.setDoctorNameEN(rs.getString(MySQLFields.DOCTOR_NAME_EN));
        bean.setDoctorSurnameEN(rs.getString(MySQLFields.DOCTOR_SURNAME_EN));
        bean.setDoctorNameUA(rs.getString(MySQLFields.DOCTOR_NAME_UA));
        bean.setDoctorSurnameUA(rs.getString(MySQLFields.DOCTOR_SURNAME_UA));
        bean.setSpecializationNameEN(rs.getString(MySQLFields.SPECIALIZATION_NAME_EN));
        bean.setSpecializationNameUA(rs.getString(MySQLFields.SPECIALIZATION_NAME_UA));
        return bean;
    }
}
