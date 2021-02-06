package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.AccountDao;
import ua.com.entity.Account;
import ua.com.exception.DBException;
import ua.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The MySQL implementation of the AccountDao interface.
 *
 * @author Orest Dmyterko
 */
public class AccountDaoImpl implements AccountDao {
    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);

    /**
     * Finds and returns account with defined login in the database,
     * otherwise returns null.
     *
     * @param login Account login
     * @return Account if found or null
     */
    @Override
    public Account findByLogin(String login) throws DBException {
        LOGGER.debug("AccountDaoImpl.findByLogin starts");
        Account account = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ACCOUNT_BY_LOGIN);
            LOGGER.info(MySQLQuery.FIND_ACCOUNT_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                account = mapAccount(rs);
            }

            DBUtil.closeResource(rs, pstmt, con);
            rs = null;
            pstmt = null;
            con = null;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DBException("Cannot find account ", e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new DBException("Cannot close Resource ", e);
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

        LOGGER.debug("AccountDaoImpl.findByLogin finishes");
        return account;
    }

    /**
     * Extracts a user from the result set.
     *
     * @param rs which should be mapped
     * @return an Account entity
     * @throws SQLException if the columnLabel is not valid;
     * if a database access error occurs or this method is called on a closed result set
     */
    private Account mapAccount(ResultSet rs) throws SQLException {
        LOGGER.debug("AccountDaoImpl.mapAccount");
        Account account = new Account();
        account.setId(rs.getLong(MySQLFields.ID));
        account.setLogin(rs.getString(MySQLFields.ACCOUNT_LOGIN));
        account.setPassword(rs.getString(MySQLFields.ACCOUNT_PASSWORD));
        account.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        account.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        account.setNonLocked(rs.getBoolean(MySQLFields.ACCOUNT_NON_LOCKED));
        account.setUpdatedBy(rs.getLong(MySQLFields.UPDATED_BY));
        account.setRoleId(rs.getInt(MySQLFields.ACCOUNT_ROLE_ID));
        account.setLocaleId(rs.getInt(MySQLFields.ACCOUNT_LOCALE_ID));
        return account;
    }
}
