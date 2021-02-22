package ua.com.dao;

import ua.com.bean.NurseAccDetailsBean;
import ua.com.entity.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Data access object for the Account entity.
 *
 * @author Orest Dmyterko
 */
public interface AccountDao {
    Account findById(Long id, Connection con) throws SQLException;

    /**
     * Returns an account by given login.
     *
     * @param login Account login
     * @return Account entity
     */
    Account findByLogin(String login);

    List<NurseAccDetailsBean> findAllNurseAccDetailsBeans();

    boolean isDoctorExists(Long id);

    boolean isPatientExists(Long id);

    boolean isNurseExists(Long id);

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

    void update(Account account);

    void update(Account account, Connection con) throws SQLException;

    boolean editDoctor(Long doctorId, Integer specializationId, String nameEN, String surnameEN, String nameUA,
                       String surnameUA, boolean locked, Long updateBy);

    boolean editPatient(Long patientId, String nameEN, String surnameEN, String nameUA,
                        String surnameUA, LocalDate birthday, boolean locked, Long updatedBy);
}
