package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.EmployeeMedicamentBean;
import ua.com.bean.MedicamentDoctorBean;
import ua.com.bean.PatientMedicamentBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.MedicamentDao;
import ua.com.entity.Medicament;
import ua.com.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the MedicamentDao interface.
 *
 * @author Orest Dmyterko
 */
public class MedicamentDaoImpl implements MedicamentDao {
    private static final Logger LOGGER = LogManager.getLogger(MedicamentDaoImpl.class);

    @Override
    public Medicament findById(Long id) {
        LOGGER.debug("findByID starts");
        Medicament medicament = new Medicament();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_MEDICAMENT_BY_ID);
            LOGGER.info(MySQLQuery.FIND_MEDICAMENT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                medicament = ObjectMapper.mapMedicament(rs);
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
        return medicament;
    }

    @Override
    public List<Medicament> findAllNotEndByMedicalCard(Long medicalCardId, Connection con) throws SQLException {
        LOGGER.debug("findAllNotEndByMedicalCard starts");
        List<Medicament> medicaments = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_NOT_END_MEDICAMENTS_BY_MEDICAL_CARD_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_NOT_END_MEDICAMENTS_BY_MEDICAL_CARD_ID);
            pstmt.setLong(1, medicalCardId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                medicaments.add(ObjectMapper.mapMedicament(rs));
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

        LOGGER.debug("findAllNotEndByMedicalCard finishes");
        return medicaments;
    }

    @Override
    public List<MedicamentDoctorBean> findAllMedicamentDoctorBeansByMedCard(Long medicalCardId) {
        LOGGER.debug("findAllMedicamentDoctorBeansByMedCard starts");
        List<MedicamentDoctorBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_MEDICAMENT_DOCTOR_BEANS_BY_MEDICAL_CARD_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_MEDICAMENT_DOCTOR_BEANS_BY_MEDICAL_CARD_ID);
            pstmt.setLong(1, medicalCardId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(ObjectMapper.mapMedicamentDoctorBean(rs));
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

        LOGGER.debug("findAllMedicamentDoctorBeansByMedCard finishes");
        return beans;
    }

    @Override
    public List<EmployeeMedicamentBean> findAllEmployeeMedicamentBeansByEmp(Long empId) {
        LOGGER.debug("findAllEmployeeMedicamentBeansByEmpId starts");
        List<EmployeeMedicamentBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_EMPLOYEE_MEDICAMENT_BEANS_BY_EMP_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_EMPLOYEE_MEDICAMENT_BEANS_BY_EMP_ID);
            pstmt.setLong(1, empId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(ObjectMapper.mapEmployeeMedicamentBean(rs));
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

        LOGGER.debug("findAllEmployeeMedicamentBeansByEmpId finishes");
        return beans;
    }

    @Override
    public List<PatientMedicamentBean> findAllPatientMedicamentBeansByPatient(Long patientId) {
        LOGGER.debug("findAllPatientMedicamentBeansByPatient starts");
        List<PatientMedicamentBean> beans = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ALL_PATIENT_MEDICAMENT_BEANS_BY_PATIENT_ID);
            LOGGER.info(MySQLQuery.FIND_ALL_PATIENT_MEDICAMENT_BEANS_BY_PATIENT_ID);
            pstmt.setLong(1, patientId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                beans.add(ObjectMapper.mapPatientMedicamentBean(rs));
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

        LOGGER.debug("findAllPatientMedicamentBeansByPatient finishes");
        return beans;
    }


    @Override
    public Medicament insert(Medicament medicament) {
        LOGGER.debug("insert starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.INSERT_MEDICAMENT, Statement.RETURN_GENERATED_KEYS);
            LOGGER.info(MySQLQuery.INSERT_MEDICAMENT);
            int k = 0;
            pstmt.setString(++k, medicament.getNameEN());
            pstmt.setString(++k, medicament.getNameUA());
            pstmt.setString(++k, medicament.getDescriptionEN());
            pstmt.setString(++k, medicament.getDescriptionUA());
            pstmt.setLong(++k, medicament.getCreatedBy());
            pstmt.setLong(++k, medicament.getServedBy());
            pstmt.setLong(++k, medicament.getMedicalCardId());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    medicament.setId(rs.getLong(1));
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
        return medicament;
    }

    @Override
    public boolean update(Medicament medicament) {
        LOGGER.debug("update starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_MEDICAMENT);
            LOGGER.info(MySQLQuery.UPDATE_MEDICAMENT);
            int k = 0;
            pstmt.setBoolean(++k, medicament.isEnd());
            pstmt.setLong(++k, medicament.getId());
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
    public void updateAllToEnd(List<Medicament> medicaments, Connection con) throws SQLException {
        LOGGER.debug("updateAllToEnd starts");
        if (medicaments.isEmpty()){
            return;
        }
        PreparedStatement pstmt = null;
        try {
            String query = DBUtil.addParamToMySqlInQuery(MySQLQuery.UPDATE_ALL_MEDICAMENTS_TO_END, medicaments.size());
            pstmt = con.prepareStatement(query);
            LOGGER.info(query);
            int k = 0;
            for (Medicament medicament : medicaments) {
                pstmt.setLong(++k, medicament.getId());
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
}
