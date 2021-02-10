package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.MySQLFields;
import ua.com.constant.MySQLQuery;
import ua.com.dao.AccountDao;
import ua.com.entity.Account;
import ua.com.util.DBUtil;

import java.sql.*;

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
    public Account findByLogin(String login) {
        LOGGER.debug("findByLogin starts");
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

        LOGGER.debug("findByLogin finishes");
        return account;
    }

    @Override
    public Account insertAccount(Connection con, Account account) throws SQLException {
        LOGGER.debug("insertAccount starts");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
            LOGGER.info(MySQLQuery.INSERT_ACCOUNT);
            int k = 0;
            pstmt.setString(++k, account.getLogin());
            pstmt.setString(++k, account.getPassword());
            pstmt.setLong(++k, account.getUpdatedBy());
            pstmt.setInt(++k, account.getRoleId());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    account.setId(rs.getLong(1));
                }
            }

            DBUtil.closeResource(rs, pstmt);
            rs = null;
            pstmt = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new SQLException(e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            rs = null;
            pstmt = null;
        }

        LOGGER.debug("insertAccount finishes");
        return account;
    }

    /**
     * Extracts a user from the result set.
     *
     * @param rs which should be mapped
     * @return an Account entity
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is called on a closed result set
     */
    private Account mapAccount(ResultSet rs) throws SQLException {
        LOGGER.debug("mapAccount starts");
        Account account = new Account();
        account.setId(rs.getLong(MySQLFields.ID));
        account.setLogin(rs.getString(MySQLFields.ACCOUNT_LOGIN));
        account.setPassword(rs.getString(MySQLFields.ACCOUNT_PASSWORD));
        account.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        account.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        account.setLocked(rs.getBoolean(MySQLFields.ACCOUNT_LOCKED));
        account.setUpdatedBy(rs.getLong(MySQLFields.UPDATED_BY));
        account.setRoleId(rs.getInt(MySQLFields.ACCOUNT_ROLE_ID));
        account.setLocaleId(rs.getInt(MySQLFields.ACCOUNT_LOCALE_ID));
        return account;
    }
}
