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
import ua.com.bean.DiagnosisDoctorBean;
import ua.com.constant.MySQLFields;
import ua.com.dao.DiagnosisDao;
import ua.com.entity.AccountDetails;
import ua.com.entity.Diagnosis;
import ua.com.entity.Specialization;
import ua.com.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({DBUtil.class})
@SuppressStaticInitializationFor("ua.com.util.DBUtil")
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class DiagnosisDaoImplTest {

    @Mock
    private Connection con;
    @Mock
    private PreparedStatement pstmt;
    @Mock
    private ResultSet rs;

    private final LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 10, 10, 10);
    private DiagnosisDoctorBean expectedDiagnosisDoctorBean;

    @Before
    public void setUp() throws SQLException {
        PowerMockito.mockStatic(DBUtil.class);
        Mockito.when(DBUtil.getConnection()).thenReturn(con);
        doNothing().when(con).close();
        when(con.prepareStatement(Mockito.anyString())).thenReturn(pstmt);
        when(con.prepareStatement(Mockito.anyString(), Mockito.anyInt())).thenReturn(pstmt);
        doNothing().when(pstmt).setLong(Mockito.anyInt(), Mockito.anyLong());
        doNothing().when(pstmt).setString(Mockito.anyInt(), Mockito.anyString());
        when(pstmt.executeQuery()).thenReturn(rs);
        when(pstmt.getGeneratedKeys()).thenReturn(rs);
        when(pstmt.executeUpdate()).thenReturn(1);
        when(rs.getLong(Mockito.anyInt())).thenReturn(5L);

        when(rs.getLong(MySQLFields.ID)).thenReturn(10L);
        when(rs.getString(MySQLFields.NAME_EN)).thenReturn("nameEN");
        when(rs.getString(MySQLFields.NAME_UA)).thenReturn("nameUA");
        when(rs.getString(MySQLFields.DESCRIPTION_EN)).thenReturn("desc");
        when(rs.getString(MySQLFields.DESCRIPTION_UA)).thenReturn("desc");
        when(rs.getTimestamp(MySQLFields.CREATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getLong(MySQLFields.CREATED_BY)).thenReturn(10L);
        when(rs.getLong(MySQLFields.MEDICAL_CARD_ID)).thenReturn(10L);
        when(rs.getString(MySQLFields.DOCTOR_NAME_EN)).thenReturn("nameEN");
        when(rs.getString(MySQLFields.DOCTOR_NAME_UA)).thenReturn("nameUA");
        when(rs.getString(MySQLFields.DOCTOR_SURNAME_EN)).thenReturn("SurnameEN");
        when(rs.getString(MySQLFields.DOCTOR_SURNAME_UA)).thenReturn("SurnameUA");
        when(rs.getString(MySQLFields.SPECIALIZATION_NAME_EN)).thenReturn("name");
        when(rs.getString(MySQLFields.SPECIALIZATION_NAME_UA)).thenReturn("name");

        AccountDetails accountDetails = new AccountDetails(10L, "nameEN",
                "SurnameEN", "nameUA", "SurnameUA");
        Specialization specialization = new Specialization("name", "name", 10L);
        expectedDiagnosisDoctorBean = new DiagnosisDoctorBean(accountDetails, specialization);
        expectedDiagnosisDoctorBean.setId(10L);
        expectedDiagnosisDoctorBean.setNameEN("nameEN");
        expectedDiagnosisDoctorBean.setNameUA("nameUA");
        expectedDiagnosisDoctorBean.setDescriptionEN("desc");
        expectedDiagnosisDoctorBean.setDescriptionUA("desc");
        expectedDiagnosisDoctorBean.setCreateTime(localDateTime);
        expectedDiagnosisDoctorBean.setCreatedBy(10L);
        expectedDiagnosisDoctorBean.setMedicalCardId(10L);
    }

    @Test
    public void insertShouldReturnGeneratedId() throws SQLException {
        Long expected = 5L;
        when(rs.next()).thenReturn(true);
        doNothing().when(pstmt).setLong(Mockito.anyInt(), Mockito.anyLong());

        DiagnosisDao dao = DaoFactory.getDiagnosisDao();
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setCreatedBy(1L);
        diagnosis.setMedicalCardId(1L);
        dao.insert(diagnosis);
        Long actual = diagnosis.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllDiagnosisDoctorBeansByMedCardShouldReturnList() throws SQLException {
        when(rs.next()).thenReturn(true, true, false);
        DiagnosisDao dao = DaoFactory.getDiagnosisDao();
        List<DiagnosisDoctorBean> beans = dao.findAllDiagnosisDoctorBeansByMedCard(10L);
        assertEquals(2, beans.size());
        assertEquals(expectedDiagnosisDoctorBean, beans.get(0));
    }
}