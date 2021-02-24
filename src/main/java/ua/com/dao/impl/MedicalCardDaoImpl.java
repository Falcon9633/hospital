package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.MedCardDoctorBean;
import ua.com.bean.MedCardPatientBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.MedicamentDao;
import ua.com.dao.ProcedureDao;
import ua.com.dao.SurgeryDao;
import ua.com.entity.MedicalCard;
import ua.com.entity.Medicament;
import ua.com.entity.Procedure;
import ua.com.entity.Surgery;
import ua.com.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the MedicalCardDao interface.
 *
 * @author Orest Dmyterko
 */
public class MedicalCardDaoImpl implements MedicalCardDao {
    public static final Logger LOGGER = LogManager.getLogger(MedicalCardDaoImpl.class);

    private MedicamentDao medicamentDao;
    private ProcedureDao procedureDao;
    private SurgeryDao surgeryDao;

    public MedicalCardDaoImpl(MedicamentDao medicamentDao, ProcedureDao procedureDao, SurgeryDao surgeryDao) {
        this.medicamentDao = medicamentDao;
        this.procedureDao = procedureDao;
        this.surgeryDao = surgeryDao;
    }

    @Override
    public MedicalCard findById(Long id) {
        LOGGER.debug("findById starts");
        MedicalCard medicalCard = new MedicalCard();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_MEDICAL_CARD_BY_ID);
            LOGGER.info(MySQLQuery.FIND_MEDICAL_CARD_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                medicalCard = mapMedicalCard(rs);
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

        LOGGER.debug("findById finishes");
        return medicalCard;
    }

    @Override
    public MedicalCard insert(MedicalCard medicalCard) {
        LOGGER.debug("insert starts");
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            medicalCard = insert(medicalCard, con);

            DBUtil.closeResource(con);
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            con = null;
        }

        LOGGER.debug("insert finishes");
        return medicalCard;
    }

    @Override
    public MedicalCard insert(MedicalCard medicalCard, Connection con) throws SQLException {
        LOGGER.debug("insert starts");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.INSERT_MEDICAL_CARD, Statement.RETURN_GENERATED_KEYS);
            LOGGER.info(MySQLQuery.INSERT_MEDICAL_CARD);
            int k = 0;
            pstmt.setLong(++k, medicalCard.getUpdatedBy());
            pstmt.setLong(++k, medicalCard.getPatientId());
            pstmt.setObject(++k, medicalCard.getDoctorId(), Types.INTEGER);
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    medicalCard.setId(rs.getLong(1));
                }
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

        return medicalCard;
    }

    @Override
    public List<MedCardDoctorBean> findAllMedCardDoctorBeanByPatient(Long patientId) {
        LOGGER.debug("findAllMedCardDoctorBeanByPatient starts");
        List<MedCardDoctorBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_MEDICAL_CARD_DOCTOR_BEANS_BY_PATIENT_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_MEDICAL_CARD_DOCTOR_BEANS_BY_PATIENT_ID);
            pstmt.setLong(1, patientId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(mapMedCardDoctorBean(rs));
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

        LOGGER.debug("findAllMedCardDoctorBeanByPatient finishes");
        return beans;
    }

    @Override
    public List<MedCardPatientBean> findAllMedCardPatientBeansByDoctor(Long doctorId, boolean discharged) {
        LOGGER.debug("findAllMedCardPatientBeansByDoctor starts");
        List<MedCardPatientBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_MEDICAL_CARD_PATIENT_BEANS_BY_DOCTOR_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_MEDICAL_CARD_PATIENT_BEANS_BY_DOCTOR_ID);
            int k = 0;
            pstmt.setLong(++k, doctorId);
            pstmt.setBoolean(++k, discharged);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(mapMedCardPatientBean(rs));
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

        LOGGER.debug("findAllMedCardPatientBeansByDoctor finishes");
        return beans;
    }

    @Override
    public List<MedCardPatientBean> findAllMedCardPatientBeansTreatedByDoctor(Long doctorId) {
        LOGGER.debug("findAllMedCardPatientBeansTreatedByDoctor starts");
        List<MedCardPatientBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_MEDICAL_CARD_PATIENT_BEANS_TREATED_BY_DOCTOR_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_MEDICAL_CARD_PATIENT_BEANS_TREATED_BY_DOCTOR_ID);
            int k = 0;
            pstmt.setLong(++k, doctorId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(mapMedCardPatientBean(rs));
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

        LOGGER.debug("findAllMedCardPatientBeansTreatedByDoctor finishes");
        return beans;
    }

    @Override
    public List<MedCardPatientBean> findAllMedCardPatientBeansByPatient(Long patientId) {
        LOGGER.debug("findAllMedCardPatientBeansByPatient starts");
        List<MedCardPatientBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_MEDICAL_CARD_PATIENT_BEANS_BY_PATIENT_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_MEDICAL_CARD_PATIENT_BEANS_BY_PATIENT_ID);
            int k = 0;
            pstmt.setLong(++k, patientId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(mapMedCardPatientBean(rs));
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

        LOGGER.debug("findAllMedCardPatientBeansByPatient finishes");
        return beans;
    }

    @Override
    public boolean update(MedicalCard medicalCard) {
        LOGGER.debug("update starts");
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            update(medicalCard, con);

            DBUtil.closeResource(con);
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
            return false;
        } finally {
            try {
                DBUtil.closeResource(con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            con = null;
        }

        return true;
    }

    @Override
    public void update(MedicalCard medicalCard, Connection con) throws SQLException {
        LOGGER.debug("update starts");
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_MEDICAL_CARD);
            LOGGER.info(MySQLQuery.UPDATE_MEDICAL_CARD);
            int k = 0;
            pstmt.setBoolean(++k, medicalCard.isDischarged());
            pstmt.setLong(++k, medicalCard.getUpdatedBy());
            pstmt.setLong(++k, medicalCard.getPatientId());
            pstmt.setLong(++k, medicalCard.getDoctorId());
            pstmt.setLong(++k, medicalCard.getId());
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
    public boolean dischargePatient(MedicalCard medicalCard) {
        LOGGER.debug("dischargePatient starts");
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            List<Medicament> medicaments = medicamentDao.findAllNotEndByMedicalCard(medicalCard.getId(), con);
            medicamentDao.updateAllToEnd(medicaments, con);

            List<Procedure> procedures = procedureDao.findAllNotEndByMedicalCard(medicalCard.getId(), con);
            procedureDao.updateAllToEnd(procedures, con);

            List<Surgery> surgeries = surgeryDao.findAllNotEndByMedicalCard(medicalCard.getId(), con);
            surgeryDao.updateAllToEnd(surgeries, con);

            update(medicalCard, con);

            con.commit();
            DBUtil.closeResource();
            con = null;
        } catch (SQLException e) {
            if (con != null) {
                DBUtil.rollback(con);
            }
            LOGGER.error(e.getMessage(), e.getCause());
            return false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            con = null;
        }
        /*
        find all active medicaments, procedures. surgeries
        set end to true
        update medical card
         */
        LOGGER.debug("dischargePatient finishes");
        return true;
    }


    private MedicalCard mapMedicalCard(ResultSet rs) throws SQLException {
        LOGGER.debug("mapMedicalCard starts");
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(rs.getLong(MySQLFields.ID));
        medicalCard.setDischarged(rs.getBoolean(MySQLFields.MEDICAL_CARD_IS_DISCHARGED));
        medicalCard.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        medicalCard.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        medicalCard.setUpdatedBy(rs.getLong(MySQLFields.UPDATED_BY));
        medicalCard.setPatientId(rs.getLong(MySQLFields.MEDICAL_CARD_PATIENT_ID));
        medicalCard.setDoctorId(rs.getLong(MySQLFields.MEDICAL_CARD_DOCTOR_ID));
        return medicalCard;
    }

    private MedCardDoctorBean mapMedCardDoctorBean(ResultSet rs) throws SQLException {
        LOGGER.debug("mapMedCardDoctorBean starts");
        MedicalCard medicalCard = mapMedicalCard(rs);
        MedCardDoctorBean bean = new MedCardDoctorBean(medicalCard);
        bean.setUpdatedByNameEN(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_EN));
        bean.setUpdatedBySurnameEN(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_EN));
        bean.setUpdatedByNameUA(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_UA));
        bean.setUpdatedBySurnameUA(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_UA));
        bean.setDoctorNameEN(rs.getString(MySQLFields.MEDICAL_CARD_DOCTOR_NAME_EN));
        bean.setDoctorSurnameEN(rs.getString(MySQLFields.MEDICAL_CARD_DOCTOR_SURNAME_EN));
        bean.setDoctorNameUA(rs.getString(MySQLFields.MEDICAL_CARD_DOCTOR_NAME_UA));
        bean.setDoctorSurnameUA(rs.getString(MySQLFields.MEDICAL_CARD_DOCTOR_SURNAME_UA));
        bean.setSpecializationNameEN(rs.getString(MySQLFields.MEDICAL_CARD_SPECIALIZATION_NAME_EN));
        bean.setSpecializationNameUA(rs.getString(MySQLFields.MEDICAL_CARD_SPECIALIZATION_NAME_UA));
        return bean;
    }

    private MedCardPatientBean mapMedCardPatientBean(ResultSet rs) throws SQLException {
        LOGGER.debug("mapMedCardPatientBean starts");
        MedicalCard medicalCard = mapMedicalCard(rs);
        MedCardPatientBean bean = new MedCardPatientBean(medicalCard);
        bean.setPatientNameEN(rs.getString(MySQLFields.PATIENT_NAME_EN));
        bean.setPatientSurnameEN(rs.getString(MySQLFields.PATIENT_SURNAME_EN));
        bean.setPatientNameUA(rs.getString(MySQLFields.PATIENT_NAME_UA));
        bean.setPatientSurnameUA(rs.getString(MySQLFields.PATIENT_SURNAME_UA));
        bean.setPatientBirthday(rs.getDate(MySQLFields.PATIENT_BIRTHDAY).toLocalDate());
        return bean;
    }
}
