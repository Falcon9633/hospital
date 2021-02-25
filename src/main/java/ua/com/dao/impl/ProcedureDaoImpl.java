package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.EmployeeProcedureBean;
import ua.com.bean.PatientProcedureBean;
import ua.com.bean.ProcedureDoctorBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.ProcedureDao;
import ua.com.entity.Procedure;
import ua.com.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the ProcedureDao interface.
 *
 * @author Orest Dmyterko
 */
public class ProcedureDaoImpl implements ProcedureDao {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureDaoImpl.class);

    @Override
    public Procedure findById(Long id) {
        LOGGER.debug("findByID starts");
        Procedure procedure = new Procedure();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_PROCEDURE_BY_ID);
            LOGGER.info(MySQLQuery.FIND_PROCEDURE_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                procedure = mapProcedure(rs);
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
        return procedure;
    }

    @Override
    public List<Procedure> findAllNotEndByMedicalCard(Long medicalCardId, Connection con) throws SQLException {
        LOGGER.debug("findAllNotEndByMedicalCard starts");
        List<Procedure> procedures = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_NOT_END_PROCEDURES_BY_MEDICAL_CARD_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_NOT_END_PROCEDURES_BY_MEDICAL_CARD_ID);
            pstmt.setLong(1, medicalCardId);
            rs = pstmt.executeQuery();

            while (rs.next()){
                procedures.add(mapProcedure(rs));
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
            } catch (Exception e){
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs= null;
            pstmt = null;
        }

        LOGGER.debug("findAllNotEndByMedicalCard finishes");
        return procedures;
    }

    @Override
    public List<ProcedureDoctorBean> findAllProcedureDoctorBeansByMedCard(Long medicalCardId) {
        LOGGER.debug("findAllProcedureDoctorBeansByMedCard starts");
        List<ProcedureDoctorBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_PROCEDURE_DOCTOR_BEANS_BY_MEDICAL_CARD_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_PROCEDURE_DOCTOR_BEANS_BY_MEDICAL_CARD_ID);
            pstmt.setLong(1, medicalCardId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(mapProcedureDoctorBean(rs));
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

        LOGGER.debug("findAllProcedureDoctorBeansByMedCard finishes");
        return beans;
    }

    @Override
    public List<EmployeeProcedureBean> findAllEmployeeProcedureBeansByEmp(Long empId) {
        LOGGER.debug("findAllEmployeeProcedureBeansByEmp starts");
        List<EmployeeProcedureBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_EMPLOYEE_PROCEDURE_BEANS_BY_EMP_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_EMPLOYEE_PROCEDURE_BEANS_BY_EMP_ID);
            pstmt.setLong(1, empId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(ObjectMapper.mapEmployeeProcedureBean(rs));
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

        LOGGER.debug("findAllEmployeeProcedureBeansByEmp finishes");
        return beans;
    }

    @Override
    public List<PatientProcedureBean> findAllPatientProcedureBeansByPatient(Long patientId) {
        LOGGER.debug("findAllPatientProcedureBeansByPatient starts");
        List<PatientProcedureBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_PATIENT_PROCEDURE_BEANS_BY_PATIENT_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_PATIENT_PROCEDURE_BEANS_BY_PATIENT_ID);
            pstmt.setLong(1, patientId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(ObjectMapper.mapPatientProcedureBean(rs));
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

        LOGGER.debug("findAllPatientProcedureBeansByPatient finishes");
        return beans;
    }

    @Override
    public Procedure insert(Procedure procedure) {
        LOGGER.debug("insert starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.INSERT_PROCEDURE, Statement.RETURN_GENERATED_KEYS);
            LOGGER.info(MySQLQuery.INSERT_PROCEDURE);
            int k = 0;
            pstmt.setString(++k, procedure.getNameEN());
            pstmt.setString(++k, procedure.getNameUA());
            pstmt.setString(++k, procedure.getDescriptionEN());
            pstmt.setString(++k, procedure.getDescriptionUA());
            pstmt.setLong(++k, procedure.getCreatedBy());
            pstmt.setLong(++k, procedure.getServedBy());
            pstmt.setLong(++k, procedure.getMedicalCardId());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    procedure.setId(rs.getLong(1));
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
        return procedure;
    }

    @Override
    public boolean update(Procedure procedure) {
        LOGGER.debug("update starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_PROCEDURE);
            LOGGER.info(MySQLQuery.UPDATE_PROCEDURE);
            int k = 0;
            pstmt.setBoolean(++k, procedure.isEnd());
            pstmt.setLong(++k, procedure.getId());
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

    @Override
    public void updateAllToEnd(List<Procedure> procedures, Connection con) throws SQLException {
        LOGGER.debug("updateAllToEnd starts");
        if (procedures.isEmpty()){
            return;
        }
        PreparedStatement pstmt = null;
        try {
            String query = DBUtil.addParamToMySqlInQuery(MySQLQuery.UPDATE_ALL_PROCEDURES_TO_END, procedures.size());
            pstmt = con.prepareStatement(query);
            LOGGER.info(query);
            int k = 0;
            for (Procedure procedure : procedures) {
                pstmt.setLong(++k, procedure.getId());
            }
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

        LOGGER.debug("updateAllToEnd finishes");
    }

    private Procedure mapProcedure(ResultSet rs) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setId(rs.getLong(MySQLFields.ID));
        procedure.setNameEN(rs.getString(MySQLFields.NAME_EN));
        procedure.setNameUA(rs.getString(MySQLFields.NAME_UA));
        procedure.setDescriptionEN(rs.getString(MySQLFields.DESCRIPTION_EN));
        procedure.setDescriptionUA(rs.getString(MySQLFields.DESCRIPTION_UA));
        procedure.setEnd(rs.getBoolean(MySQLFields.END));
        procedure.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        procedure.setCreatedBy(rs.getLong(MySQLFields.CREATED_BY));
        procedure.setServedBy(rs.getLong(MySQLFields.SERVED_BY));
        procedure.setMedicalCardId(rs.getLong(MySQLFields.MEDICAL_CARD_ID));
        return procedure;
    }

    private ProcedureDoctorBean mapProcedureDoctorBean(ResultSet rs) throws SQLException {
        Procedure procedure = mapProcedure(rs);
        ProcedureDoctorBean bean = new ProcedureDoctorBean(procedure);
        bean.setDoctorNameEN(rs.getString(MySQLFields.DOCTOR_NAME_EN));
        bean.setDoctorSurnameEN(rs.getString(MySQLFields.DOCTOR_SURNAME_EN));
        bean.setDoctorNameUA(rs.getString(MySQLFields.DOCTOR_NAME_UA));
        bean.setDoctorSurnameUA(rs.getString(MySQLFields.DOCTOR_SURNAME_UA));
        bean.setSpecializationNameEN(rs.getString(MySQLFields.SPECIALIZATION_NAME_EN));
        bean.setSpecializationNameUA(rs.getString(MySQLFields.SPECIALIZATION_NAME_UA));
        bean.setServedByNameEN(rs.getString(MySQLFields.SERVED_BY_NAME_EN));
        bean.setServedBySurnameEN(rs.getString(MySQLFields.SERVED_BY_SURNAME_EN));
        bean.setServedByNameUA(rs.getString(MySQLFields.SERVED_BY_NAME_UA));
        bean.setServedBySurnameUA(rs.getString(MySQLFields.SERVED_BY_SURNAME_UA));
        return bean;
    }
}
