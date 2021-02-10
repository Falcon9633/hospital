package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.SpecializationDao;
import ua.com.entity.Specialization;
import ua.com.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            while (rs.next()){
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

    private Specialization mapSpecialization(ResultSet rs) throws SQLException {
        LOGGER.debug("mapSpecialization starts");
        Specialization specialization = new Specialization();
        specialization.setId(rs.getInt(MySQLFields.ID));
        specialization.setNameEN(rs.getString(MySQLFields.NAME_EN));
        specialization.setNameUA(rs.getString(MySQLFields.NAME_UA));
        return specialization;
    }
}
