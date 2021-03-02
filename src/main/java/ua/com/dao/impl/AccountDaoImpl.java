package ua.com.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.bean.NurseAccDetailsBean;
import ua.com.constant.MySQLQuery;
import ua.com.dao.AccountDao;
import ua.com.dao.AccountDetailsDao;
import ua.com.dao.DoctorDao;
import ua.com.dao.PatientDao;
import ua.com.entity.*;
import ua.com.util.DBUtil;
import ua.com.util.MailSender;
import ua.com.util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The MySQL implementation of the AccountDao interface.
 *
 * @author Orest Dmyterko
 */
public class AccountDaoImpl implements AccountDao {
    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);

    private AccountDetailsDao accountDetailsDao;
    private DoctorDao doctorDao;
    private PatientDao patientDao;

    public AccountDaoImpl(AccountDetailsDao accountDetailsDao, DoctorDao doctorDao, PatientDao patientDao) {
        this.accountDetailsDao = accountDetailsDao;
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
    }

    @Override
    public Account findById(Long id, Connection con) throws SQLException {
        LOGGER.debug("findById starts");
        Account account = new Account();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.FIND_ACCOUNT_BY_ID);
            LOGGER.info(MySQLQuery.FIND_ACCOUNT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                account = ObjectMapper.mapAccount(rs);
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

        LOGGER.debug("findById finishes");
        return account;
    }

    private boolean isAccountExistByIdAndRole(Long id, int roleId) {
        LOGGER.debug("findByIdAndRole");
        boolean exist = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(MySQLQuery.FIND_ACCOUNT_BY_ID_AND_ROLE);
            LOGGER.info(MySQLQuery.FIND_ACCOUNT_BY_ID_AND_ROLE);
            int k = 0;
            pstmt.setLong(++k, id);
            pstmt.setInt(++k, roleId);
            exist = pstmt.execute();

            DBUtil.closeResource(pstmt, con);
            pstmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(pstmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            pstmt = null;
            con = null;
        }
        return exist;
    }

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
                account = ObjectMapper.mapAccount(rs);
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
    public List<NurseAccDetailsBean> findAllNurseAccDetailsBeans() {
        LOGGER.debug("findAllNurseAccDetailsBeans starts");
        List<NurseAccDetailsBean> beans = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MySQLQuery.FIND_ALL_NURSE_ACCOUNT_DETAILS_BEANS);
            LOGGER.info(MySQLQuery.FIND_ALL_NURSE_ACCOUNT_DETAILS_BEANS);

            while (rs.next()) {
                beans.add(ObjectMapper.mapNurseAccDetailsBean(rs));
            }

            DBUtil.closeResource(rs, stmt, con);
            rs = null;
            stmt = null;
            con = null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, stmt, con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            stmt = null;
            con = null;
        }

        LOGGER.debug("findAllNurseAccDetailsBeans finishes");
        return beans;
    }

    @Override
    public boolean isDoctorExists(Long id) {
        return isAccountExistByIdAndRole(id, Role.DOCTOR.ordinal());
    }

    @Override
    public boolean isPatientExists(Long id) {
        return isAccountExistByIdAndRole(id, Role.PATIENT.ordinal());
    }

    @Override
    public boolean isNurseExists(Long id) {
        return isAccountExistByIdAndRole(id, Role.NURSE.ordinal());
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
            pstmt.setString(++k, account.getEmail());
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
            LOGGER.error(e.getMessage(), e.getCause());
            throw new SQLException(e.getCause());
        } finally {
            try {
                DBUtil.closeResource(rs, pstmt);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            rs = null;
            pstmt = null;
        }

        LOGGER.debug("insertAccount finishes");
        return account;
    }

    @Override
    public void update(Account account) {
        LOGGER.debug("update starts");
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            update(account, con);

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
    }

    @Override
    public void update(Account account, Connection con) throws SQLException {
        LOGGER.debug("update starts");
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(MySQLQuery.UPDATE_ACCOUNT);
            LOGGER.info(MySQLQuery.UPDATE_ACCOUNT);
            int k = 0;
            pstmt.setString(++k, account.getLogin());
            pstmt.setString(++k, account.getPassword());
            pstmt.setString(++k, account.getEmail());
            pstmt.setBoolean(++k, account.isLocked());
            pstmt.setLong(++k, account.getUpdatedBy());
            pstmt.setInt(++k, account.getRoleId());
            pstmt.setInt(++k, account.getLocaleId());
            pstmt.setLong(++k, account.getId());
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

    @Override
    public boolean registerAccount(String login, Long updatedBy, Role role, String nameEN, String surnameEN,
                                   String nameUA, String surnameUA, String email, Integer specializationId, LocalDate birthday) {
        LOGGER.debug("registerAccount starts");
        Connection con = null;
        try {
            String pwd = PasswordUtil.generateRandomPassword();
            String encryptedPwd = PasswordUtil.encryptPassword(pwd);
            Account account = new Account(login, encryptedPwd, email, updatedBy, role.ordinal());

            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            insertAccount(con, account);

            AccountDetails accountDetails = new AccountDetails(account.getId(), nameEN, surnameEN, nameUA, surnameUA);
            accountDetailsDao.insert(con, accountDetails);

            if (role == Role.DOCTOR) {
                Doctor doctor = new Doctor(account.getId(), specializationId);
                doctorDao.insertDoctor(con, doctor);
            }

            if (role == Role.PATIENT) {
                Patient patient = new Patient(account.getId(), birthday);
                patientDao.insertPatient(con, patient);
            }

            MailSender.send(email, login, pwd);
            con.commit();
            con.close();
            con = null;
        } catch (SQLException e) {
            if (con != null) {
                DBUtil.rollback(con);
            }
            LOGGER.error(e.getMessage(), e.getCause());
            return false;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            return false;
        } finally {
            try {
                DBUtil.closeResource(con);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
            }
            con = null;
        }

        LOGGER.debug("registerAccount finishes");
        return true;
    }

    @Override
    public boolean editDoctor(Long doctorId, Integer specializationId, String nameEN, String surnameEN, String nameUA,
                              String surnameUA, boolean locked, Long updateBy) {
        LOGGER.debug("editDoctor starts");
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            editUser(doctorId, nameEN, surnameEN, nameUA, surnameUA, locked, updateBy, con);

            Doctor doctor = doctorDao.findById(doctorId, con);
            doctor.setSpecializationId(specializationId);
            doctorDao.update(doctor, con);

            con.commit();
            DBUtil.closeResource();
            con = null;
        } catch (SQLException e) {
            if (con != null) {
                DBUtil.rollback(con);
            }
            LOGGER.error(e.getMessage(), e.getCause());
            return false;
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

        LOGGER.debug("editDoctor finishes");
        return true;
    }

    @Override
    public boolean editPatient(Long patientId, String nameEN, String surnameEN, String nameUA, String surnameUA,
                               LocalDate birthday, boolean locked, Long updatedBy) {
        LOGGER.debug("editPatient starts");
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            editUser(patientId, nameEN, surnameEN, nameUA, surnameUA, locked, updatedBy, con);

            Patient patient = patientDao.findById(patientId, con);
            patient.setBirthday(birthday);
            patientDao.update(patient, con);

            con.commit();
            DBUtil.closeResource();
            con = null;
        } catch (SQLException e) {
            if (con != null) {
                DBUtil.rollback(con);
            }
            LOGGER.error(e.getMessage(), e.getCause());
            return false;
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

        LOGGER.debug("editPatient finishes");
        return true;
    }

    private void editUser(Long id, String nameEN, String surnameEN, String nameUA, String surnameUA, boolean locked,
                          Long updatedBy, Connection con) throws SQLException {
        LOGGER.debug("editUser starts");
        try {
            Account account = findById(id, con);
            AccountDetails accountDetails = accountDetailsDao.findById(id, con);

            account.setLocked(locked);
            account.setUpdatedBy(updatedBy);

            accountDetails.setNameEN(nameEN);
            accountDetails.setSurnameEN(surnameEN);
            accountDetails.setNameUA(nameUA);
            accountDetails.setSurnameUA(surnameUA);

            update(account, con);
            accountDetailsDao.update(accountDetails, con);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new SQLException(e.getCause());
        }
    }
}
