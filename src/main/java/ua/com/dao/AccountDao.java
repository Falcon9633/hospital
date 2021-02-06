package ua.com.dao;

import ua.com.entity.Account;
import ua.com.exception.DBException;

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
    Account findByLogin(String login) throws DBException;
}
