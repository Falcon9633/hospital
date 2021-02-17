package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.SpecializationAccountDetailsBean;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.SpecializationDao;
import ua.com.entity.Specialization;
import ua.com.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the SpecializationDao interface.
 *
 * @author Orest Dmyterko
 */
public class SpecializationDaoImpl implements SpecializationDao {
    private static final Logger LOGGER = LogManager.getLogger(SpecializationDaoImpl.class);

    /**
     * Finds and returns all specializations in the database.
     *
     * @return all specializations
     */
    @Override
    public List<Specialization> findAll() {
        LOGGER.debug("findAll starts");
        List<Specialization> specializations = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MySQLQuery.FIND_ALL_SPECIALIZATIONS);
            LOGGER.info(MySQLQuery.FIND_ALL_SPECIALIZATIONS);
            while (rs.next()) {
                specializations.add(mapSpecialization(rs));
            }
            DBUtil.closeResource(rs, stmt, con);
            rs = null;
            stmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                DBUtil.closeResource();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            rs = null;
            stmt = null;
            con = null;
        }

        LOGGER.debug("findAll finishes");
        return specializations;
    }

    @Override
    public List<SpecializationAccountDetailsBean> findAllSpecAccDetailsBeans() {
        LOGGER.debug("findAllSpecAccDetailsBeans starts");
        List<SpecializationAccountDetailsBean> beans = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MySQLQuery.FIND_ALL_SPECIALIZATION_ACC_DETAILS_BEANS);
            LOGGER.info(MySQLQuery.FIND_ALL_SPECIALIZATION_ACC_DETAILS_BEANS);
            while (rs.next()) {
                beans.add(mapSpecAccDetailsBean(rs));
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

        LOGGER.debug("findAllSpecAccDetailsBeans finishes");
        return beans;
    }

    @Override
    public Specialization findById(Integer id) {
        LOGGER.debug("findById starts");
        Specialization specialization = new Specialization();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_SPECIALIZATION_BY_ID);
            LOGGER.info(MySQLQuery.FIND_SPECIALIZATION_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                specialization = mapSpecialization(rs);
            }

            DBUtil.closeResource(rs, pstmt, con);
            rs = null;
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            rs = null;
            pstmt = null;
            con = null;
        }

        LOGGER.debug("findById finishes");
        return specialization;
    }

    @Override
    public List<Specialization> findByName(String nameEN, String nameUA) {
        LOGGER.debug("findByName starts");
        List<Specialization> specializations = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_SPECIALIZATION_BY_NAME);
            LOGGER.info(MySQLQuery.FIND_SPECIALIZATION_BY_NAME);
            int k = 0;
            pstmt.setString(++k, nameEN);
            pstmt.setString(++k, nameUA);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                specializations.add(mapSpecialization(rs));
            }

            DBUtil.closeResource(rs, pstmt, con);
            rs = null;
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            rs = null;
            pstmt = null;
            con = null;
        }

        LOGGER.debug("findByName finishes");
        return specializations;
    }

    @Override
    public Specialization insert(Specialization specialization) {
        LOGGER.debug("insert starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.INSERT_SPECIALIZATION, Statement.RETURN_GENERATED_KEYS);
            LOGGER.info(MySQLQuery.INSERT_SPECIALIZATION);
            int k = 0;
            pstmt.setString(++k, specialization.getNameEN());
            pstmt.setString(++k, specialization.getNameUA());
            pstmt.setLong(++k, specialization.getUpdatedBy());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    specialization.setId(rs.getInt(1));
                }
            }

            DBUtil.closeResource(rs, pstmt, con);
            rs = null;
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            rs = null;
            pstmt = null;
            con = null;
        }

        LOGGER.debug("insert finishes");
        return specialization;
    }

    @Override
    public boolean update(Specialization specialization) {
        LOGGER.debug("update starts");
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_SPECIALIZATION);
            LOGGER.info(MySQLQuery.UPDATE_SPECIALIZATION);
            int k = 0;
            pstmt.setString(++k, specialization.getNameEN());
            pstmt.setString(++k, specialization.getNameUA());
            pstmt.setLong(++k, specialization.getUpdatedBy());
            pstmt.setInt(++k, specialization.getId());
            pstmt.executeUpdate();

            DBUtil.closeResource(pstmt, con);
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        } finally {
            try {
                DBUtil.closeResource(pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            pstmt = null;
            con = null;
        }

        LOGGER.debug("update finishes");
        return true;
    }

    private Specialization mapSpecialization(ResultSet rs) throws SQLException {
        LOGGER.debug("mapSpecialization starts");
        Specialization specialization = new Specialization();
        specialization.setId(rs.getInt(MySQLFields.ID));
        specialization.setNameEN(rs.getString(MySQLFields.NAME_EN));
        specialization.setNameUA(rs.getString(MySQLFields.NAME_UA));
        specialization.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        specialization.setUpdatedBy(rs.getLong(MySQLFields.UPDATED_BY));
        return specialization;
    }

    private SpecializationAccountDetailsBean mapSpecAccDetailsBean(ResultSet rs) throws SQLException {
        LOGGER.debug("mapSpecAccDetailsBean starts");
        SpecializationAccountDetailsBean bean = new SpecializationAccountDetailsBean();
        bean.setId(rs.getInt(MySQLFields.ID));
        bean.setNameEN(rs.getString(MySQLFields.NAME_EN));
        bean.setNameUA(rs.getString(MySQLFields.NAME_UA));
        bean.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        bean.setAccountNameEN(rs.getString(MySQLFields.UPDATE_BY_ACC_DETAILS_NAME_EN));
        bean.setAccountSurnameEN(rs.getString(MySQLFields.UPDATE_BY_ACC_DETAILS_SURNAME_EN));
        bean.setAccountNameUA(rs.getString(MySQLFields.UPDATE_BY_ACC_DETAILS_NAME_UA));
        bean.setAccountSurnameUA(rs.getString(MySQLFields.UPDATE_BY_ACC_DETAILS_SURNAME_UA));
        return bean;
    }
}
