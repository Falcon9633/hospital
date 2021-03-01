package ua.com.dao.impl;

import org.junit.Before;
import org.junit.BeforeClass;
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
import ua.com.dao.PatientDao;
import ua.com.entity.Patient;
import ua.com.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class PatientDaoImplTest {
    @Mock
    private Connection con;
    @Mock
    private Statement stmt;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private static Patient patient;

    @BeforeClass
    public static void setUpClass() {
        patient = new Patient(10L, LocalDate.now());
    }

    @Before
    public void setUp() throws SQLException {
        PowerMockito.mockStatic(DBUtil.class);
        Mockito.when(DBUtil.getConnection()).thenReturn(con);
        doNothing().when(con).commit();
        doNothing().when(con).close();
        when(con.createStatement()).thenReturn(stmt);
        when(con.prepareStatement(anyString())).thenReturn(pstmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);
        when(pstmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
    }

    @Test
    public void findByIdTest() throws SQLException {
        PatientDao dao = DaoFactory.getPatientDao();
        dao.findById(10L, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test(expected = SQLException.class)
    public void findByIdWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        PatientDao dao = DaoFactory.getPatientDao();
        dao.findById(10L, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void insertPatientTest() throws SQLException {
        PatientDao dao = DaoFactory.getPatientDao();
        dao.insertPatient(con, patient);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).setDate(anyInt(), any());
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test(expected = SQLException.class)
    public void insertPatientWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        PatientDao dao = DaoFactory.getPatientDao();
        dao.insertPatient(con, patient);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).setDate(anyInt(), any());
        verify(pstmt, never()).executeUpdate();
    }

    @Test
    public void findAllPatientAccountBeansTest() throws SQLException {
        PatientDao dao = DaoFactory.getPatientDao();
        dao.findAllPatientAccountBeans(10, 10, "", "");

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).setInt(anyInt(), anyInt());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllPatientAccountBeansWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        PatientDao dao = DaoFactory.getPatientDao();
        dao.findAllPatientAccountBeans(10, 10, "", "");

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).setInt(anyInt(), anyInt());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void getNumberOfRowsTest() throws SQLException {
        PatientDao dao = DaoFactory.getPatientDao();
        dao.getNumberOfRows();

        verify(con, times(1)).createStatement();
        verify(stmt, times(1)).executeQuery(anyString());
        verify(rs, times(1)).next();
        verify(rs, times(1)).getLong(anyString());
    }

    @Test
    public void getNumberOfRowsWithStmtException() throws SQLException {
        when(con.createStatement()).thenThrow(new SQLException());
        PatientDao dao = DaoFactory.getPatientDao();
        dao.getNumberOfRows();

        verify(con, times(1)).createStatement();
        verify(stmt, never()).executeQuery(anyString());
        verify(rs, never()).next();
        verify(rs, never()).getLong(anyInt());
    }

    @Test
    public void updateTest() throws SQLException {
        PatientDao dao = DaoFactory.getPatientDao();
        dao.update(patient, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setDate(anyInt(), any());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test(expected = SQLException.class)
    public void updateTestWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        PatientDao dao = DaoFactory.getPatientDao();
        dao.update(patient, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setDate(anyInt(), any());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
    }
}