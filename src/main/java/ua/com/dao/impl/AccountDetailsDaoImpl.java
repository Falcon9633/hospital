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
}
