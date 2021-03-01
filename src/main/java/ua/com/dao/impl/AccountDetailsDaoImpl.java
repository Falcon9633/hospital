package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.MySQLQuery;
import ua.com.dao.AccountDetailsDao;
import ua.com.entity.AccountDetails;
import ua.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The MySQL implementation of the AccountDetailsDao interface.
 *
 * @author Orest Dmyterko
 */
public class AccountDetailsDaoImpl implements AccountDetailsDao {
    private static final Logger LOGGER = LogManager.getLogger(AccountDetailsDaoImpl.class);

    @Override
    public AccountDetails findById(Long id) {
        LOGGER.debug("findById starts");
        AccountDetails accountDetails = null;
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            accountDetails = findById(id, con);

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

        LOGGER.debug("findById finishes");
        return accountDetails;
    }

    @Override
    public AccountDetails findById(Long id, Connection con) throws SQLException {
        LOGGER.debug("findById starts");
        AccountDetails accountDetails = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.FIND_ACCOUNT_DETAILS_BY_ID);
            LOGGER.info(MySQLQuery.FIND_ACCOUNT_DETAILS_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                accountDetails = ObjectMapper.mapAccountDetails(rs);
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

        return accountDetails;
    }

    @Override
    public AccountDetails insert(Connection con, AccountDetails accountDetails) throws SQLException {
        LOGGER.debug("insertAccountDetails starts");
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.INSERT_ACCOUNT_DETAILS);
            LOGGER.info(MySQLQuery.INSERT_ACCOUNT_DETAILS);
            int k = 0;
            pstmt.setLong(++k, accountDetails.getId());
            pstmt.setString(++k, accountDetails.getNameEN());
            pstmt.setString(++k, accountDetails.getSurnameEN());
            pstmt.setString(++k, accountDetails.getNameUA());
            pstmt.setString(++k, accountDetails.getSurnameUA());
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

        LOGGER.debug("insertAccountDetails finishes");
        return accountDetails;
    }

    @Override
    public void update(AccountDetails accountDetails, Connection con) throws SQLException {
        LOGGER.debug("update starts");
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_ACCOUNT_DETAILS);
            LOGGER.info(MySQLQuery.UPDATE_ACCOUNT_DETAILS);
            int k = 0;
            pstmt.setString(++k, accountDetails.getNameEN());
            pstmt.setString(++k, accountDetails.getSurnameEN());
            pstmt.setString(++k, accountDetails.getNameUA());
            pstmt.setString(++k, accountDetails.getSurnameUA());
            pstmt.setLong(++k, accountDetails.getId());
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
