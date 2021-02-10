package ua.com.dao;

import ua.com.entity.Account;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data access object for the Account entity.
 *
 * @author Orest Dmyterko
 */
public interface AccountDao {
    /**
     * Returns an account by given login.
     *
     * @param login Account login
     * @return Account entity
     */
    Account findByLogin(String login);

    /**
     * Inserts given account to the database.
     *
     * @param con     Connection to the db
     * @param account to insert into db
     * @return inserted account
     * @throws SQLException if the given connection closed
     * @see Connection
     * @see java.sql.PreparedStatement
     */
    Account insertAccount(Connection con, Account account) throws SQLException;
}
