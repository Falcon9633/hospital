package ua.com.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import ua.com.bean.NurseAccDetailsBean;
import ua.com.constant.MySQLFields;
import ua.com.dao.AccountDao;
import ua.com.dao.AccountDetailsDao;
import ua.com.dao.DoctorDao;
import ua.com.dao.PatientDao;
import ua.com.entity.*;
import ua.com.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class AccountDaoImplTest {

    @Mock
    private Connection con;
    @Mock
    private Statement stmt;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private final LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 10, 10, 10);
    private final Account expectedAccount = new Account();
    private NurseAccDetailsBean expectedNurseAccDetailsBean;

    @Before
    public void setUp() throws SQLException {
        PowerMockito.mockStatic(DBUtil.class);
        Mockito.when(DBUtil.getConnection()).thenReturn(con);
        doNothing().when(con).setAutoCommit(false);
        doNothing().when(con).commit();
        doNothing().when(con).close();
        when(con.prepareStatement(Mockito.anyString())).thenReturn(pstmt);
        when(con.prepareStatement(Mockito.anyString(), Mockito.anyInt())).thenReturn(pstmt);
        when(con.createStatement()).thenReturn(stmt);
        doNothing().when(pstmt).setLong(Mockito.anyInt(), Mockito.anyLong());
        doNothing().when(pstmt).setInt(Mockito.anyInt(), Mockito.anyInt());
        doNothing().when(pstmt).setString(Mockito.anyInt(), Mockito.anyString());
        when(pstmt.executeQuery()).thenReturn(rs);
        when(pstmt.getGeneratedKeys()).thenReturn(rs);
        when(pstmt.executeUpdate()).thenReturn(1);
        when(stmt.executeQuery(Mockito.anyString())).thenReturn(rs);

        when(rs.getLong(anyInt())).thenReturn(5L);
        when(rs.getLong(MySQLFields.ID)).thenReturn(10L);
        when(rs.getString(MySQLFields.ACCOUNT_LOGIN)).thenReturn("Login");
        when(rs.getString(MySQLFields.ACCOUNT_PASSWORD)).thenReturn("Password");
        when(rs.getString(MySQLFields.ACCOUNT_EMAIL)).thenReturn("Email");
        when(rs.getTimestamp(MySQLFields.CREATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getTimestamp(MySQLFields.UPDATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getBoolean(MySQLFields.ACCOUNT_LOCKED)).thenReturn(false);
        when(rs.getLong(MySQLFields.UPDATED_BY)).thenReturn(1L);
        when(rs.getInt(MySQLFields.ACCOUNT_ROLE_ID)).thenReturn(1);
        when(rs.getInt(MySQLFields.ACCOUNT_LOCALE_ID)).thenReturn(1);

        when(rs.getString(MySQLFields.NAME_EN)).thenReturn("NameEN");
        when(rs.getString(MySQLFields.NAME_UA)).thenReturn("NameUA");
        when(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_EN)).thenReturn("SurnameEN");
        when(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_UA)).thenReturn("SurnameUA");

        expectedAccount.setId(10L);
        expectedAccount.setLogin("Login");
        expectedAccount.setPassword("Password");
        expectedAccount.setEmail("Email");
        expectedAccount.setCreateTime(localDateTime);
        expectedAccount.setUpdateTime(localDateTime);
        expectedAccount.setLocked(false);
        expectedAccount.setUpdatedBy(1L);
        expectedAccount.setRoleId(1);
        expectedAccount.setLocaleId(1);

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(10L);
        accountDetails.setNameEN("NameEN");
        accountDetails.setNameUA("NameUA");
        accountDetails.setSurnameEN("SurnameEN");
        accountDetails.setSurnameUA("SurnameUA");

        expectedNurseAccDetailsBean = new NurseAccDetailsBean(expectedAccount, accountDetails);
    }

    @Test
    public void findByIdShouldReturnAccount() throws SQLException {
        when(rs.next()).thenReturn(true);
        AccountDao dao = DaoFactory.getAccountDao();
        Account actualAccount = dao.findById(10L, con);
        assertEquals(expectedAccount, actualAccount);
    }

    @Test(expected = SQLException.class)
    public void findByIdShouldThrowSqlException() throws SQLException {
        when(con.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());

        AccountDao dao = DaoFactory.getAccountDao();
        dao.findById(10L, con);
    }

    @Test(expected = Exception.class)
    public void findByIdShouldThrowException() throws SQLException {
        doThrow(new Exception()).when(con).close();

        AccountDao dao = DaoFactory.getAccountDao();
        dao.findById(10L, con);
    }

    @Test
    public void findByLoginShouldReturnAccount() throws SQLException {
        when(rs.next()).thenReturn(true);
        AccountDao dao = DaoFactory.getAccountDao();
        Account actualAccount = dao.findByLogin("Login");
        assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void findByLoginShouldCatchException() throws Exception {
        when(con.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());

        AccountDao dao = DaoFactory.getAccountDao();
        dao.findByLogin("Login");
    }

    @Test
    public void findAllNurseAccDetailsBeansShouldReturnList() throws SQLException {
        when(rs.next()).thenReturn(true, true, false);
        AccountDao dao = DaoFactory.getAccountDao();
        List<NurseAccDetailsBean> allNurseAccDetailsBeans = dao.findAllNurseAccDetailsBeans();
        assertEquals(2, allNurseAccDetailsBeans.size());
        assertEquals(expectedNurseAccDetailsBean, allNurseAccDetailsBeans.get(0));
    }

    @Test
    public void findAllNurseAccDetailsBeansShouldCatchException() throws SQLException {
        when(con.createStatement()).thenThrow(new SQLException());

        AccountDao dao = DaoFactory.getAccountDao();
        dao.findAllNurseAccDetailsBeans();
    }

    @Test
    public void isDoctorExistsShouldReturnTrueIfExists() throws SQLException {
        when(pstmt.execute()).thenReturn(true);

        AccountDao dao = DaoFactory.getAccountDao();
        assertTrue(dao.isDoctorExists(10L));
    }

    @Test
    public void isDoctorExistsShouldReturnFalseIfNotExists() throws SQLException {
        when(pstmt.execute()).thenReturn(false);

        AccountDao dao = DaoFactory.getAccountDao();
        assertFalse(dao.isDoctorExists(10L));
    }

    @Test
    public void isNurseExistsShouldReturnTrueIfExists() throws SQLException {
        when(pstmt.execute()).thenReturn(true);

        AccountDao dao = DaoFactory.getAccountDao();
        assertTrue(dao.isNurseExists(10L));
    }

    @Test
    public void isNurseExistsShouldReturnFalseIfNotExists() throws SQLException {
        when(pstmt.execute()).thenReturn(false);

        AccountDao dao = DaoFactory.getAccountDao();
        assertFalse(dao.isNurseExists(10L));
    }

    @Test
    public void isPatientExistsShouldReturnTrueIfExists() throws SQLException {
        when(pstmt.execute()).thenReturn(true);

        AccountDao dao = DaoFactory.getAccountDao();
        assertTrue(dao.isPatientExists(10L));
    }

    @Test
    public void isPatientExistsShouldReturnFalseIfNotExists() throws SQLException {
        when(pstmt.execute()).thenReturn(false);

        AccountDao dao = DaoFactory.getAccountDao();
        assertFalse(dao.isPatientExists(10L));
    }

    @Test
    public void insertAccountShouldReturnGeneratedId() throws SQLException {
        Long expected = 5L;
        when(rs.next()).thenReturn(true);

        AccountDao dao = DaoFactory.getAccountDao();
        Account account = dao.insertAccount(con, new Account());
        Long id = account.getId();
        assertEquals(expected, id);
    }

    @Test
    public void insertAccountShouldThrowSqlException() throws SQLException {
        when(con.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());

        AccountDao dao = DaoFactory.getAccountDao();
        dao.insertAccount(con, new Account());
    }

    @Test
    public void updateShouldCatchException() throws SQLException {
        when(pstmt.executeUpdate()).thenThrow(new SQLException());

        AccountDao dao = DaoFactory.getAccountDao();
        dao.update(new Account());
    }

    @Test
    public void registerAccountDoctorShouldReturnTrueOnSuccess() throws Exception {
        AccountDetailsDao accountDetailsDao = mock(AccountDetailsDaoImpl.class);
        when(accountDetailsDao.insert(con, new AccountDetails())).thenReturn(new AccountDetails());
        DoctorDao doctorDao = mock(DoctorDaoImpl.class);
        when(doctorDao.insertDoctor(con, new Doctor())).thenReturn(new Doctor());
        PatientDao patientDao = mock(PatientDaoImpl.class);
        when(patientDao.insertPatient(con, new Patient())).thenReturn(new Patient());


        AccountDao dao = new AccountDaoImpl(accountDetailsDao, doctorDao, patientDao);

        boolean success = dao.registerAccount("null", 10L, Role.DOCTOR, "null",
                "null", "null", "null", "null", 10, LocalDate.now());
        assertTrue(success);
    }

    @Test
    public void registerAccountPatientShouldReturnTrueOnSuccess() throws Exception {
        AccountDetailsDao accountDetailsDao = mock(AccountDetailsDaoImpl.class);
        when(accountDetailsDao.insert(con, new AccountDetails())).thenReturn(new AccountDetails());
        DoctorDao doctorDao = mock(DoctorDaoImpl.class);
        when(doctorDao.insertDoctor(con, new Doctor())).thenReturn(new Doctor());
        PatientDao patientDao = mock(PatientDaoImpl.class);
        when(patientDao.insertPatient(con, new Patient())).thenReturn(new Patient());


        AccountDao dao = new AccountDaoImpl(accountDetailsDao, doctorDao, patientDao);

        boolean success = dao.registerAccount("null", 10L, Role.PATIENT, "null",
                "null", "null", "null", "null", 10, LocalDate.now());
        assertTrue(success);
    }

    @Test
    public void registerAccountShouldReturnFalseOnFailure() {
        AccountDao dao = DaoFactory.getAccountDao();
        boolean success = dao.registerAccount("null", 10L, Role.DOCTOR, "null",
                "null", "null", "null", "null", 10, LocalDate.now());
        assertFalse(success);
    }

    @Test
    public void editDoctorShouldReturnTrueOnSuccess() throws SQLException {
        AccountDetailsDao accountDetailsDao = mock(AccountDetailsDaoImpl.class);
        when(accountDetailsDao.findById(10L, con)).thenReturn(new AccountDetails());
        DoctorDao doctorDao = mock(DoctorDaoImpl.class);
        when(doctorDao.findById(10L, con)).thenReturn(new Doctor());
        PatientDao patientDao = mock(PatientDaoImpl.class);
        when(patientDao.insertPatient(con, new Patient())).thenReturn(new Patient());

        AccountDao dao = new AccountDaoImpl(accountDetailsDao, doctorDao, patientDao);
        boolean success = dao.editDoctor(10L, 10, "null", "null", "null",
                "null", false, 10L);
        assertTrue(success);
    }

    @Test
    public void editDoctorShouldReturnFalseOnFailure() throws SQLException {
        doThrow(new SQLException()).when(con).setAutoCommit(false);

        AccountDao dao = DaoFactory.getAccountDao();
        boolean success = dao.editDoctor(10L, 10, "null", "null", "null",
                "null", false, 10L);
        assertFalse(success);
    }

    @Test
    public void editPatientShouldReturnTrueOnSuccess() throws SQLException {
        AccountDetailsDao accountDetailsDao = mock(AccountDetailsDaoImpl.class);
        when(accountDetailsDao.findById(10L, con)).thenReturn(new AccountDetails());
        DoctorDao doctorDao = mock(DoctorDaoImpl.class);
        when(doctorDao.findById(10L, con)).thenReturn(new Doctor());
        PatientDao patientDao = mock(PatientDaoImpl.class);
        when(patientDao.findById(10L, con)).thenReturn(new Patient());

        AccountDao dao = new AccountDaoImpl(accountDetailsDao, doctorDao, patientDao);
        boolean success = dao.editPatient(10L, "null", "null", "null",
                "null", LocalDate.now(), false, 10L);
        assertTrue(success);
    }

    @Test
    public void editPatientShouldReturnFalseOnFailure() throws SQLException {
        doThrow(new SQLException()).when(con).setAutoCommit(false);

        AccountDao dao = DaoFactory.getAccountDao();
        boolean success = dao.editPatient(10L, "null", "null", "null",
                "null", LocalDate.now(), false, 10L);
        assertFalse(success);
    }
}