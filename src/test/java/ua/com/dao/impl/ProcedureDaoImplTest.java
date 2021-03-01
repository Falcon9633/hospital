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
import ua.com.dao.ProcedureDao;
import ua.com.entity.Procedure;
import ua.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class ProcedureDaoImplTest {
    @Mock
    private Connection con;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private static Procedure procedure;

    @BeforeClass
    public static void setUpClass(){
        procedure = new Procedure("nameEN", "nameUA", "descEN", "descUA",
                10L, 10L, 10L);
        procedure.setId(10L);
    }

    @Before
    public void setUp() throws SQLException {
        PowerMockito.mockStatic(DBUtil.class);
        Mockito.when(DBUtil.getConnection()).thenReturn(con);
        Mockito.when(DBUtil.addParamToMySqlInQuery(anyString(), anyInt())).thenReturn("");
        doNothing().when(con).commit();
        doNothing().when(con).close();
        when(con.prepareStatement(anyString())).thenReturn(pstmt);
        when(con.prepareStatement(anyString(), anyInt())).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(rs);
        when(pstmt.executeUpdate()).thenReturn(1);
        when(pstmt.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
    }

    @Test
    public void findByIdTest() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findById(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findByIdWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findById(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllNotEndByMedicalCardTest() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findAllNotEndByMedicalCard(10L, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test(expected = SQLException.class)
    public void findAllNotEndByMedicalCardWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findAllNotEndByMedicalCard(10L, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllProcedureDoctorBeansByMedCardTest() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findAllProcedureDoctorBeansByMedCard(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllProcedureDoctorBeansByMedCardPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findAllProcedureDoctorBeansByMedCard(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllEmployeeProcedureBeansByEmpTest() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findAllEmployeeProcedureBeansByEmp(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllEmployeeProcedureBeansByEmpWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findAllEmployeeProcedureBeansByEmp(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllPatientProcedureBeansByPatientTest() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findAllPatientProcedureBeansByPatient(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllPatientProcedureBeansByPatientWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.findAllPatientProcedureBeansByPatient(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void insertTest() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.insert(procedure);

        verify(con, times(1)).prepareStatement(anyString(), anyInt());
        verify(pstmt, times(4)).setString(anyInt(), anyString());
        verify(pstmt, times(3)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
        verify(pstmt, times(1)).getGeneratedKeys();
        verify(rs, times(1)).next();
        verify(rs, times(1)).getLong(anyInt());
    }

    @Test
    public void insertWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException());
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.insert(procedure);

        verify(con, times(1)).prepareStatement(anyString(), anyInt());
        verify(pstmt, never()).setString(anyInt(), anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
        verify(pstmt, never()).getGeneratedKeys();
        verify(rs, never()).next();
        verify(rs, never()).getLong(anyInt());
    }

    @Test
    public void updateTest() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        boolean actual = dao.update(procedure);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setBoolean(anyInt(), anyBoolean());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
        assertTrue(actual);
    }

    @Test
    public void updateWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        ProcedureDao dao = DaoFactory.getProcedureDao();
        boolean actual = dao.update(procedure);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setBoolean(anyInt(), anyBoolean());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
        assertFalse(actual);
    }

    @Test
    public void updateAllToEndTest() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.updateAllToEnd(Collections.singletonList(procedure), con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, atLeastOnce()).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    public void updateAllToEndEmptyMedicaments() throws SQLException {
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.updateAllToEnd(new ArrayList<>(), con);

        verify(con, never()).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
    }

    @Test(expected = SQLException.class)
    public void updateAllToEndWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        ProcedureDao dao = DaoFactory.getProcedureDao();
        dao.updateAllToEnd(Collections.singletonList(procedure), con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
    }
}