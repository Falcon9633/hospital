package ua.com.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.dao.AccountDao;
import ua.com.dao.AccountDetailsDao;
import ua.com.dao.DoctorDao;
import ua.com.dao.PatientDao;
import ua.com.dao.impl.AccountDaoImpl;
import ua.com.dao.impl.AccountDetailsDaoImpl;
import ua.com.dao.impl.DoctorDaoImpl;
import ua.com.dao.impl.PatientDaoImpl;
import ua.com.entity.*;
import ua.com.service.AccountService;
import ua.com.util.DBUtil;
import ua.com.util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    public boolean registerAccount(String login, Long updateBy, Role role, String nameEN, String surnameEN, String nameUA,
                                   String surnameUA, String email, Integer specializationId, LocalDate birthday) {
        LOGGER.debug("registerAccount starts");
        Connection con = null;
        try {
            String pwd = PasswordUtil.generateRandomPassword();
            String encryptedPwd = PasswordUtil.encryptPassword(pwd);
            Account account = new Account(login, updateBy, role.ordinal());
            account.setPassword(encryptedPwd);

            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            AccountDao accountDao = new AccountDaoImpl();
            Account insertedAccount = accountDao.insertAccount(con, account);
            LOGGER.trace("inserted account -> {}", insertedAccount);

            AccountDetails accountDetails = new AccountDetails(account.getId(), nameEN, surnameEN, nameUA, surnameUA, email);
            AccountDetailsDao accountDetailsDao = new AccountDetailsDaoImpl();
            AccountDetails insertedAccountDetails = accountDetailsDao.insertAccountDetails(con, accountDetails);
            LOGGER.trace("inserted accountDetails -> {}", insertedAccountDetails);

            if (role == Role.DOCTOR) {
                Doctor doctor = new Doctor(account.getId(), specializationId);
                DoctorDao doctorDao = new DoctorDaoImpl();
                Doctor insertedDoctor = doctorDao.insertDoctor(con, doctor);
                LOGGER.trace("inserted doctor -> {}", insertedDoctor);
            }

            if (role == Role.PATIENT) {
                Patient patient = new Patient(account.getId(), birthday);
                PatientDao patientDao = new PatientDaoImpl();
                Patient insertedPatient = patientDao.insertPatient(con, patient);
                LOGGER.trace("inserted patient -> {}", insertedPatient);
            }

            con.commit();
            con.close();
            con = null;
        } catch (SQLException e) {
            if (con != null) {
                DBUtil.rollback(con);
            }
            LOGGER.error(e.getMessage());
            return false;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
            return false;
        } finally {
            try {
                DBUtil.closeResource(con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            con = null;
        }

        LOGGER.debug("registerAccount finishes");
        return true;
    }

}
