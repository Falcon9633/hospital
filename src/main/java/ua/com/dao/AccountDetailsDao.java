package ua.com.dao;

import ua.com.entity.AccountDetails;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data access object for the AccountDetails entity.
 *
 * @author Orest Dmyterko
 */
public interface AccountDetailsDao {
    /**
     * Returns accountDetails by given identifier.
     *
     * @param id AccountDetails identifier
     * @return AccountDetails entity
     */
    AccountDetails findById(Long id);

    AccountDetails findById(Long id, Connection con) throws SQLException;

    /**
     * Inserts given accountDetails to the database.
     *
     * @param con            Connection to the db
     * @param accountDetails to insert into db
     * @return inserted accountDetails
     * @throws SQLException if the given connection closed
     * @see Connection
     * @see java.sql.PreparedStatement
     */
    AccountDetails insertAccountDetails(Connection con, AccountDetails accountDetails) throws SQLException;

    void update(AccountDetails accountDetails, Connection con) throws SQLException;
}
