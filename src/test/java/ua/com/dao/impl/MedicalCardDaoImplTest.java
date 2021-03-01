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
import ua.com.bean.MedCardDoctorBean;
import ua.com.constant.MySQLFields;
import ua.com.dao.MedicalCardDao;
import ua.com.entity.MedicalCard;
import ua.com.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class MedicalCardDaoImplTest {
    @Mock
    private Connection con;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private static final LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 10, 10, 10);
    private static MedicalCard expectedMedicalCard;
    private static MedCardDoctorBean expectedMedCardDoctorBean;

    @BeforeClass
    public static void setUpClass() {
        expectedMedicalCard = new MedicalCard();
        expectedMedicalCard.setId(10L);
        expectedMedicalCard.setDischarged(false);
        expectedMedicalCard.setCreateTime(localDateTime);
        expectedMedicalCard.setUpdateTime(localDateTime);
        expectedMedicalCard.setUpdatedBy(10L);
        expectedMedicalCard.setPatientId(10L);
        expectedMedicalCard.setDoctorId(10L);

        expectedMedCardDoctorBean = new MedCardDoctorBean();
        expectedMedCardDoctorBean.setId(10L);
        expectedMedicalCard.setDischarged(false);
        expectedMedCardDoctorBean.setCreateTime(localDateTime);
        expectedMedCardDoctorBean.setUpdateTime(localDateTime);
        expectedMedCardDoctorBean.setUpdatedBy(10L);
        expectedMedCardDoctorBean.setPatientId(10L);
        expectedMedCardDoctorBean.setDoctorId(10L);
        expectedMedCardDoctorBean.setDoctorNameEN("doctorNameEN");
        expectedMedCardDoctorBean.setDoctorNameUA("doctorNameUA");
        expectedMedCardDoctorBean.setDoctorSurnameEN("doctorSurnameEN");
        expectedMedCardDoctorBean.setDoctorSurnameUA("doctorSurnameUA");
        expectedMedCardDoctorBean.setUpdatedByNameEN("updateNameEN");
        expectedMedCardDoctorBean.setUpdatedByNameUA("updateNameUA");
        expectedMedCardDoctorBean.setUpdatedBySurnameEN("updateSurnameEN");
        expectedMedCardDoctorBean.setUpdatedBySurnameUA("updateSurnameUA");
        expectedMedCardDoctorBean.setSpecializationNameEN("specNameEN");
        expectedMedCardDoctorBean.setSpecializationNameUA("specNameUA");
    }

    @Before
    public void setUp() throws SQLException {
        PowerMockito.mockStatic(DBUtil.class);
        Mockito.when(DBUtil.getConnection()).thenReturn(con);
        doNothing().when(con).commit();
        doNothing().when(con).close();
        when(con.prepareStatement(anyString())).thenReturn(pstmt);
        when(con.prepareStatement(Mockito.anyString(), Mockito.anyInt())).thenReturn(pstmt);
        doNothing().when(pstmt).setLong(anyInt(), anyLong());
        when(pstmt.executeQuery()).thenReturn(rs);
        when(pstmt.executeUpdate()).thenReturn(1);
        when(pstmt.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        when(rs.getLong(MySQLFields.ID)).thenReturn(10L);
        when(rs.getBoolean(MySQLFields.MEDICAL_CARD_IS_DISCHARGED)).thenReturn(false);
        when(rs.getTimestamp(MySQLFields.CREATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getTimestamp(MySQLFields.UPDATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getLong(MySQLFields.UPDATED_BY)).thenReturn(10L);
        when(rs.getLong(MySQLFields.MEDICAL_CARD_PATIENT_ID)).thenReturn(10L);
        when(rs.getLong(MySQLFields.MEDICAL_CARD_DOCTOR_ID)).thenReturn(10L);

        when(rs.getString(MySQLFields.DOCTOR_NAME_EN)).thenReturn("doctorNameEN");
        when(rs.getString(MySQLFields.DOCTOR_NAME_UA)).thenReturn("doctorNameUA");
        when(rs.getString(MySQLFields.DOCTOR_SURNAME_EN)).thenReturn("doctorSurnameEN");
        when(rs.getString(MySQLFields.DOCTOR_SURNAME_UA)).thenReturn("doctorSurnameUA");

        when(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_EN)).thenReturn("updateNameEN");
        when(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_UA)).thenReturn("updateNameUA");
        when(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_EN)).thenReturn("updateSurnameEN");
        when(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_UA)).thenReturn("updateSurnameUA");

        when(rs.getString(MySQLFields.SPECIALIZATION_NAME_EN)).thenReturn("specNameEN");
        when(rs.getString(MySQLFields.SPECIALIZATION_NAME_UA)).thenReturn("specNameUA");
    }

    @Test
    public void findByIdTest() throws SQLException {
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findById(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findByIdWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findById(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
    }

    @Test
    public void insertTest() throws SQLException {
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.insert(expectedMedicalCard);

        verify(con, times(1)).prepareStatement(anyString(), anyInt());
        verify(pstmt, times(2)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).setObject(anyInt(), anyLong(), anyInt());
        verify(pstmt, times(1)).executeUpdate();
        verify(pstmt, times(1)).getGeneratedKeys();
        verify(rs, times(1)).next();
        verify(rs, times(1)).getLong(anyInt());
    }

    @Test
    public void insertWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException());
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.insert(expectedMedicalCard);

        verify(con, times(1)).prepareStatement(anyString(), anyInt());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).setObject(anyInt(), anyLong(), anyInt());
        verify(pstmt, never()).executeUpdate();
        verify(pstmt, never()).getGeneratedKeys();
        verify(rs, never()).next();
        verify(rs, never()).getLong(anyInt());
    }

    @Test
    public void findAllMedCardDoctorBeanByPatientTest() throws SQLException {
        when(rs.next()).thenReturn(true, true, false);
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        List<MedCardDoctorBean> bean = dao.findAllMedCardDoctorBeanByPatient(10L);
        assertEquals(2, bean.size());
        assertEquals(expectedMedCardDoctorBean, bean.get(0));
    }

    @Test
    public void findAllMedCardDoctorBeanByPatientWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findAllMedCardDoctorBeanByPatient(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllMedCardPatientBeansByDoctorTest() throws SQLException {
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findAllMedCardPatientBeansByDoctor(10L, false);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).setBoolean(anyInt(), anyBoolean());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllMedCardPatientBeansByDoctorWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findAllMedCardPatientBeansByDoctor(10L, false);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).setBoolean(anyInt(), anyBoolean());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllMedCardPatientBeansTreatedByDoctorTest() throws SQLException {
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findAllMedCardPatientBeansTreatedByDoctor(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllMedCardPatientBeansTreatedByDoctorWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findAllMedCardPatientBeansTreatedByDoctor(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void findAllMedCardPatientBeansByPatientTest() throws SQLException {
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findAllMedCardPatientBeansByPatient(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeQuery();
        verify(rs, times(1)).next();
    }

    @Test
    public void findAllMedCardPatientBeansByPatientWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.findAllMedCardPatientBeansByPatient(10L);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeQuery();
        verify(rs, never()).next();
    }

    @Test
    public void updateTest() throws SQLException {
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.update(expectedMedicalCard);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, times(1)).setBoolean(anyInt(), anyBoolean());
        verify(pstmt, times(4)).setLong(anyInt(), anyLong());
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    public void updateWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        dao.update(expectedMedicalCard);

        verify(con, times(1)).prepareStatement(anyString());
        verify(pstmt, never()).setBoolean(anyInt(), anyBoolean());
        verify(pstmt, never()).setLong(anyInt(), anyLong());
        verify(pstmt, never()).executeUpdate();
    }

    @Test
    public void dischargePatientTest() throws SQLException {
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        boolean actual = dao.dischargePatient(expectedMedicalCard);

        verify(con, times(1)).setAutoCommit(anyBoolean());
        verify(con, times(1)).commit();
        assertTrue(actual);
    }

    @Test
    public void dischargePatientWithPstmtException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException());
        MedicalCardDao dao = DaoFactory.getMedicalCardDao();
        boolean actual = dao.dischargePatient(expectedMedicalCard);

        verify(con, times(1)).setAutoCommit(anyBoolean());
        verify(con, never()).commit();
        assertFalse(actual);
    }
}