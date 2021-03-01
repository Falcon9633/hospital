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
import ua.com.dao.SurgeryDao;
import ua.com.entity.Surgery;
import ua.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class SurgeryDaoImplTest {
    @Mock
    private Connection con;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private static Surgery surgery;

    @BeforeClass
    public static void setUpClass(){
        surgery = new Surgery("nameEN", "nameUA", "descEN", "descUA",
                10L, 10L, 10L);
        surgery.setId(10L);
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
        when(pstmt.executeUpdate()).thenReturn(1);
        when(pstmt.executeQuery()).thenReturn(rs);
        when(pstmt.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
    }

    @Test
    public void findByIdTest() throws SQLException {
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findById(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findByIdWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findById(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllNotEndByMedicalCardTest() throws SQLException {
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findAllNotEndByMedicalCard(10L, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test(expected = SQLException.class)
    public void findAllNotEndByMedicalCardWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findAllNotEndByMedicalCard(10L, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllMedicamentDoctorBeansByMedCardTest() throws SQLException {
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findAllSurgeryDoctorBeansByMedCard(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllMedicamentDoctorBeansByMedCardWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findAllSurgeryDoctorBeansByMedCard(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllEmployeeMedicamentBeansByEmpTest() throws SQLException {
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findAllEmployeeSurgeryBeansByEmp(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllEmployeeMedicamentBeansByEmpWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findAllEmployeeSurgeryBeansByEmp(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllPatientMedicamentBeansByPatientTest() throws SQLException {
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findAllPatientSurgeryBeansByPatient(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllPatientMedicamentBeansByPatientWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.findAllPatientSurgeryBeansByPatient(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void insertTest() throws SQLException {
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.insert(surgery);

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
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.insert(surgery);

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
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        boolean actual = dao.update(surgery);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setBoolean(anyInt(), anyBoolean());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
        assertTrue(actual);
    }

    @Test
    public void updateWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        boolean actual = dao.update(surgery);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setBoolean(anyInt(), anyBoolean());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
        assertFalse(actual);
    }

    @Test
    public void updateAllToEndTest() throws SQLException {
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.updateAllToEnd(Collections.singletonList(surgery), con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, atLeastOnce()).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    public void updateAllToEndEmptyMedicaments() throws SQLException {
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.updateAllToEnd(new ArrayList<>(), con);

        verify(con, never()).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
    }

    @Test(expected = SQLException.class)
    public void updateAllToEndWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        SurgeryDao dao = DaoFactory.getSurgeryDao();
        dao.updateAllToEnd(Collections.singletonList(surgery), con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
    }
}