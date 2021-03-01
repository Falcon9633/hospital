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
import ua.com.bean.DoctorAccDetailsBean;
import ua.com.bean.DoctorAccountBean;
import ua.com.constant.MySQLFields;
import ua.com.dao.DoctorDao;
import ua.com.entity.Doctor;
import ua.com.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class DoctorDaoImplTest {
    @Mock
    private Connection con;
    @Mock
    private Statement stmt;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private static final LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 10, 10, 10);
    private static Doctor expectedDoctor;
    private static DoctorAccDetailsBean expectedDoctorAccDetailsBean;
    private static DoctorAccountBean expectedDoctorAccountBean;


    @BeforeClass
    public static void setUpClass() {
        expectedDoctor = new Doctor();
        expectedDoctor.setId(10L);
        expectedDoctor.setSpecializationId(10);
        expectedDoctorAccDetailsBean = new DoctorAccDetailsBean();
        expectedDoctorAccDetailsBean.setId(10L);
        expectedDoctorAccDetailsBean.setSpecializationId(10);
        expectedDoctorAccDetailsBean.setNameEN("nameEN");
        expectedDoctorAccDetailsBean.setNameUA("nameUA");
        expectedDoctorAccDetailsBean.setSurnameEN("surnameEN");
        expectedDoctorAccDetailsBean.setSurnameUA("surnameUA");
        expectedDoctorAccountBean = new DoctorAccountBean();
        expectedDoctorAccountBean.setId(10L);
        expectedDoctorAccountBean.setSpecializationId(10);
        expectedDoctorAccountBean.setNameEN("nameEN");
        expectedDoctorAccountBean.setNameUA("nameUA");
        expectedDoctorAccountBean.setSurnameEN("surnameEN");
        expectedDoctorAccountBean.setSurnameUA("surnameUA");
        expectedDoctorAccountBean.setEmail("email");
        expectedDoctorAccountBean.setLocked(false);
        expectedDoctorAccountBean.setCreateTime(localDateTime);
        expectedDoctorAccountBean.setUpdateTime(localDateTime);
        expectedDoctorAccountBean.setUpdatedByNameEN("updateNameEN");
        expectedDoctorAccountBean.setUpdatedByNameUA("updateNameUA");
        expectedDoctorAccountBean.setUpdatedBySurnameEN("updateSurnameEN");
        expectedDoctorAccountBean.setUpdatedBySurnameUA("updateSurnameUA");
        expectedDoctorAccountBean.setPatientCount(1);
        expectedDoctorAccountBean.setSpecializationNameEN("specNameEN");
        expectedDoctorAccountBean.setSpecializationNameUA("specNameUA");
    }

    @Before
    public void setUp() throws SQLException {
        PowerMockito.mockStatic(DBUtil.class);
        Mockito.when(DBUtil.getConnection()).thenReturn(con);
        doNothing().when(con).commit();
        doNothing().when(con).close();
        when(con.createStatement()).thenReturn(stmt);
        when(con.prepareStatement(anyString())).thenReturn(pstmt);
        doNothing().when(pstmt).setLong(anyInt(), anyLong());
        doNothing().when(pstmt).setInt(anyInt(), anyInt());
        when(stmt.executeQuery(anyString())).thenReturn(rs);
        when(pstmt.executeQuery()).thenReturn(rs);
        when(pstmt.execute()).thenReturn(true);
        when(rs.next()).thenReturn(true, false);

        when(rs.getLong(MySQLFields.ID)).thenReturn(10L);
        when(rs.getInt(MySQLFields.DOCTOR_SPECIALIZATION_ID)).thenReturn(10);

        when(rs.getString(MySQLFields.NAME_EN)).thenReturn("nameEN");
        when(rs.getString(MySQLFields.NAME_UA)).thenReturn("nameUA");
        when(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_EN)).thenReturn("surnameEN");
        when(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_UA)).thenReturn("surnameUA");

        when(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_EN)).thenReturn("updateNameEN");
        when(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_UA)).thenReturn("updateNameUA");
        when(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_EN)).thenReturn("updateSurnameEN");
        when(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_UA)).thenReturn("updateSurnameUA");

        when(rs.getString(MySQLFields.SPECIALIZATION_NAME_EN)).thenReturn("specNameEN");
        when(rs.getString(MySQLFields.SPECIALIZATION_NAME_UA)).thenReturn("specNameUA");

        when(rs.getString(MySQLFields.ACCOUNT_EMAIL)).thenReturn("email");
        when(rs.getBoolean(MySQLFields.ACCOUNT_LOCKED)).thenReturn(false);
        when(rs.getTimestamp(MySQLFields.CREATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getTimestamp(MySQLFields.UPDATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getInt(MySQLFields.DOCTOR_PATIENT_COUNT)).thenReturn(1);
    }

    @Test
    public void findByIdShouldReturnDoctor() throws SQLException {
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        Doctor actualDoctor = doctorDao.findById(10L, con);
        assertEquals(expectedDoctor, actualDoctor);
    }

    @Test(expected = SQLException.class)
    public void findByIdShouldThrowSqlException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());

        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        doctorDao.findById(10L, con);
    }

    @Test
    public void insertDoctorTest() throws SQLException {
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        doctorDao.insertDoctor(con, expectedDoctor);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).setInt(anyInt(), anyInt());
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test(expected = SQLException.class)
    public void insertDoctorShouldThrowSqlExceptionTest() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());

        try {
            DoctorDao doctorDao = DaoFactory.getDoctorDao();
            doctorDao.insertDoctor(con, expectedDoctor);
        } catch (SQLException e) {
            verify(con, times(1)).prepareStatement(anyString());
            verify(pstmt, never()).setLong(anyInt(), anyLong());
            verify(pstmt, never()).setInt(anyInt(), anyInt());
            verify(pstmt, never()).executeUpdate();
            throw e;
        }
    }

    @Test
    public void updateTest() throws SQLException {
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        doctorDao.update(expectedDoctor, con);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setInt(anyInt(), anyInt());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test(expected = SQLException.class)
    public void updateShouldThrowSqlException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());

        try {
            DoctorDao doctorDao = DaoFactory.getDoctorDao();
            doctorDao.update(expectedDoctor, con);
        } catch (SQLException e) {
            verify(con, times(1)).prepareStatement(anyString());
            verify(pstmt, never()).setInt(anyInt(), anyInt());
            verify(pstmt, never()).setLong(anyInt(), anyLong());
            verify(pstmt, never()).executeUpdate();
            throw e;
        }
    }

    @Test
    public void findAllDoctorAccDetailsBeansTest() throws SQLException {
        when(rs.next()).thenReturn(true, true, false);
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        List<DoctorAccDetailsBean> allDoctorAccDetailsBeans = doctorDao.findAllDoctorAccDetailsBeans();
        assertEquals(2, allDoctorAccDetailsBeans.size());
        assertEquals(expectedDoctorAccDetailsBean, allDoctorAccDetailsBeans.get(0));
    }

    @Test
    public void findAllDoctorAccDetailsBeansShouldCatchException() throws SQLException {
        when(con.createStatement()).thenThrow(new SQLException());
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        doctorDao.findAllDoctorAccDetailsBeans();
    }

    @Test
    public void findAllDoctorAccountBeansTest() throws SQLException {
        when(rs.next()).thenReturn(true, true, false);
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        List<DoctorAccountBean> allDoctorAccountBeans = doctorDao.findAllDoctorAccountBeans();
        assertEquals(2, allDoctorAccountBeans.size());
        assertEquals(expectedDoctorAccountBean, allDoctorAccountBeans.get(0));
    }

    @Test
    public void findAllDoctorAccountBeansShouldCatchException() throws SQLException {
        when(con.createStatement()).thenThrow(new SQLException());
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        doctorDao.findAllDoctorAccountBeans();
    }

    @Test
    public void isDoctorTreatedPatientTest() throws SQLException {
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        doctorDao.isDoctorTreatedPatient(10L, 10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(2)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).execute();
    }

    @Test
    public void isDoctorTreatedPatientShouldCatchException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        DoctorDao doctorDao = DaoFactory.getDoctorDao();
        doctorDao.isDoctorTreatedPatient(10L, 10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).execute();
    }
}