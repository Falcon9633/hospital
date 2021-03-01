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
import ua.com.constant.MySQLFields;
import ua.com.dao.AccountDetailsDao;
import ua.com.entity.AccountDetails;
import ua.com.util.DBUtil;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class AccountDetailsDaoImplTest {

    @Mock
    private Connection con;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private final AccountDetails expectedAccDetails = new AccountDetails();

    @Before
    public void setUp() throws SQLException {
        PowerMockito.mockStatic(DBUtil.class);
        Mockito.when(DBUtil.getConnection()).thenReturn(con);
        doNothing().when(con).setAutoCommit(false);
        doNothing().when(con).commit();
        doNothing().when(con).close();
        when(con.prepareStatement(Mockito.anyString())).thenReturn(pstmt);
        when(con.prepareStatement(Mockito.anyString(), Mockito.anyInt())).thenReturn(pstmt);
        doNothing().when(pstmt).setLong(Mockito.anyInt(), Mockito.anyLong());
        doNothing().when(pstmt).setInt(Mockito.anyInt(), Mockito.anyInt());
        doNothing().when(pstmt).setString(Mockito.anyInt(), Mockito.anyString());
        when(pstmt.executeQuery()).thenReturn(rs);
        when(pstmt.getGeneratedKeys()).thenReturn(rs);
        when(pstmt.executeUpdate()).thenReturn(1);
        when(rs.getLong(Mockito.anyInt())).thenReturn(5L);

        when(rs.getLong(MySQLFields.ID)).thenReturn(10L);
        when(rs.getString(MySQLFields.NAME_EN)).thenReturn("NameEN");
        when(rs.getString(MySQLFields.NAME_UA)).thenReturn("NameUA");
        when(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_EN)).thenReturn("SurnameEN");
        when(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_UA)).thenReturn("SurnameUA");

        expectedAccDetails.setId(10L);
        expectedAccDetails.setNameEN("NameEN");
        expectedAccDetails.setNameUA("NameUA");
        expectedAccDetails.setSurnameEN("SurnameEN");
        expectedAccDetails.setSurnameUA("SurnameUA");
    }

    @Test
    public void findByIdShouldReturnAccountDetails() throws SQLException {
        when(rs.next()).thenReturn(true);
        AccountDetailsDao dao = DaoFactory.getAccountDetailsDao();
        AccountDetails actual = dao.findById(10L);
        assertEquals(expectedAccDetails, actual);
    }

    @Test(expected = SQLException.class)
    public void findByIdShouldReturnThrowSqlException() throws SQLException {
        when(con.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());

        AccountDetailsDao dao = DaoFactory.getAccountDetailsDao();
        dao.findById(10L, con);
    }

    @Test
    public void insertShouldReturnGeneratedId() throws SQLException {
        Long expected = 10L;
        when(rs.next()).thenReturn(true);

        AccountDetailsDao dao = DaoFactory.getAccountDetailsDao();
        AccountDetails account = dao.insert(con, expectedAccDetails);
        Long id = account.getId();
        assertEquals(expected, id);
    }

    @Test(expected = SQLException.class)
    public void updateShouldThrowSqlException() throws SQLException {
        when(pstmt.executeUpdate()).thenThrow(new SQLException());

        AccountDetailsDao dao = DaoFactory.getAccountDetailsDao();
        dao.update(expectedAccDetails, con);
    }

}