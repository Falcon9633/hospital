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
import ua.com.dao.SpecializationDao;
import ua.com.entity.Specialization;
import ua.com.util.DBUtil;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class SpecializationDaoImplTest {
    @Mock
    private Connection con;
    @Mock
    private Statement stmt;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private static Specialization specialization;

    @BeforeClass
    public static void setUpClass() {
        specialization = new Specialization("nameEN", "nameUA", 10L);
        specialization.setId(10);
    }

    @Before
    public void setUp() throws SQLException {
        PowerMockito.mockStatic(DBUtil.class);
        Mockito.when(DBUtil.getConnection()).thenReturn(con);
        doNothing().when(con).commit();
        doNothing().when(con).close();
        when(con.createStatement()).thenReturn(stmt);
        when(con.prepareStatement(anyString())).thenReturn(pstmt);
        when(con.prepareStatement(anyString(), anyInt())).thenReturn(pstmt);
        when(pstmt.executeUpdate()).thenReturn(1);
        when(stmt.executeQuery(anyString())).thenReturn(rs);
        when(pstmt.executeQuery()).thenReturn(rs);
        when(pstmt.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
    }

    @Test
    public void findAllTest() throws SQLException {
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.findAll();

        verify(con, times(1)).createStatement();
        verify(stmt, times(1)).executeQuery(anyString());
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllWithStmtException() throws SQLException {
        when(con.createStatement()).thenThrow(new SQLException());
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.findAll();

        verify(con, times(1)).createStatement();
        verify(stmt, never()).executeQuery(anyString());
        verify(rs, never()).next();
    }

    @Test
    public void findAllSpecAccDetailsBeansTest() throws SQLException {
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.findAllSpecAccDetailsBeans();

        verify(con, times(1)).createStatement();
        verify(stmt, times(1)).executeQuery(anyString());
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllSpecAccDetailsBeansWithStmtException() throws SQLException {
        when(con.createStatement()).thenThrow(new SQLException());
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.findAllSpecAccDetailsBeans();

        verify(con, times(1)).createStatement();
        verify(stmt, never()).executeQuery(anyString());
        verify(rs, never()).next();
    }

    @Test
    public void findByIdTest() throws SQLException {
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.findById(10);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setInt(anyInt(), anyInt());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findByIdWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.findById(10);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setInt(anyInt(), anyInt());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findByNameTest() throws SQLException {
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.findByName("nameEN", "nameUA");

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(2)).setString(anyInt(), anyString());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findByNameWithStmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.findByName("nameEN", "nameUA");

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setString(anyInt(), anyString());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void insertTest() throws SQLException {
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.insert(specialization);

        verify(con, times(1)).prepareStatement(anyString(), anyInt());
        verify(pstmt, times(2)).setString(anyInt(), anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
        verify(pstmt, times(1)).getGeneratedKeys();
        verify(rs, times(1)).next();
        verify(rs, times(1)).getInt(anyInt());
    }

    @Test
    public void insertTestWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException());
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        dao.insert(specialization);

        verify(con, times(1)).prepareStatement(anyString(), anyInt());
        verify(pstmt, never()).setString(anyInt(), anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
        verify(pstmt, never()).getGeneratedKeys();
        verify(rs, never()).next();
        verify(rs, never()).getInt(anyInt());
    }

    @Test
    public void updateTest() throws SQLException {
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        boolean actual = dao.update(specialization);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(2)).setString(anyInt(), anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).setInt(anyInt(), anyInt());
        verify(pstmt, times(1)).executeUpdate();
        assertTrue(actual);
    }

    @Test
    public void updateWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SpecializationDao dao = DaoFactory.getSpecializationDao();
        boolean actual = dao.update(specialization);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setString(anyInt(), anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).setInt(anyInt(), anyInt());
        verify(pstmt, never()).executeUpdate();
        assertFalse(actual);
    }
}