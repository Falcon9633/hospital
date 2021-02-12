package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.MySQLFields;
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
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ACCOUNT_DETAILS_BY_ID);
            LOGGER.info(MySQLQuery.FIND_ACCOUNT_DETAILS_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                accountDetails = mapAccountDetails(rs);
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
        return accountDetails;
    }

    @Override
    public AccountDetails insertAccountDetails(Connection con, AccountDetails accountDetails) throws SQLException {
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
            pstmt.setString(++k, accountDetails.getEmail());
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

    /**
     * Extracts accountDetails from the result set.
     *
     * @param rs which should be mapped
     * @return AccountDetails entity
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is called on a closed result set
     */
    private AccountDetails mapAccountDetails(ResultSet rs) throws SQLException {
        LOGGER.debug("mapAccountDetails starts");
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(rs.getLong(MySQLFields.ID));
        accountDetails.setNameEN(rs.getString(MySQLFields.NAME_EN));
        accountDetails.setSurnameEN(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_EN));
        accountDetails.setNameUA(rs.getString(MySQLFields.NAME_UA));
        accountDetails.setSurnameUA(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_UA));
        accountDetails.setEmail(rs.getString(MySQLFields.ACCOUNT_DETAILS_EMAIL));
        accountDetails.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        return accountDetails;
    }
}
