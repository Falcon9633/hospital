package ua.com.dao.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import ua.com.bean.*;
import ua.com.constant.MySQLFields;
import ua.com.entity.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class ObjectMapperTest {
    @Mock
    private ResultSet rs;

    private static LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 10, 10, 10);
    private static LocalDate localDate = LocalDate.of(2020, 10, 10);
    private static Account expectedAccount;
    private static AccountDetails expectedAccountDetails;
    private static MedicalCard expectedMedicalCard;
    private static Doctor expectedDoctor;
    private static Patient expectedPatient;
    private static Specialization expectedSpecialization;
    private static Medicament expectedMedicament;
    private static Procedure expectedProcedure;
    private static Surgery expectedSurgery;
    private static EmployeeMedicamentBean expectedEmployeeMedicamentBean;
    private static EmployeeSurgeryBean expectedEmployeeSurgeryBean;
    private static EmployeeProcedureBean expectedEmployeeProcedureBean;
    private static PatientMedicamentBean expectedPatientMedicamentBean;
    private static PatientProcedureBean expectedPatientProcedureBean;
    private static PatientSurgeryBean expectedPatientSurgeryBean;

    @BeforeClass
    public static void setUpClass() {
        expectedAccount = new Account("Login", "Password", "Email", 10L, 1);
        expectedAccount.setId(10L);
        expectedAccount.setCreateTime(localDateTime);
        expectedAccount.setUpdateTime(localDateTime);
        expectedAccount.setLocked(false);
        expectedAccount.setLocaleId(1);

        expectedAccountDetails = new AccountDetails(10L, "nameEN", "surnameEN",
                "nameUA", "surnameUA");

        expectedMedicalCard = new MedicalCard(10L, 10L);
        expectedMedicalCard.setId(10L);
        expectedMedicalCard.setDischarged(false);
        expectedMedicalCard.setCreateTime(localDateTime);
        expectedMedicalCard.setUpdateTime(localDateTime);
        expectedMedicalCard.setDoctorId(10L);

        expectedDoctor = new Doctor(10L, 10);

        expectedPatient = new Patient(10L, localDate);

        expectedSpecialization = new Specialization("nameEN", "nameUA", 10L);
        expectedSpecialization.setId(10);
        expectedSpecialization.setUpdateTime(localDateTime);
        expectedSpecialization.setUpdatedBy(10L);

        expectedMedicament = new Medicament("nameEN", "nameUA", "descEN",
                "descUA", 10L, 10L, 10L);
        expectedMedicament.setId(10L);
        expectedMedicament.setCreateTime(localDateTime);

        expectedProcedure = new Procedure("nameEN", "nameUA", "descEN",
                "descUA", 10L, 10L, 10L);
        expectedProcedure.setId(10L);
        expectedProcedure.setCreateTime(localDateTime);

        expectedSurgery = new Surgery("nameEN", "nameUA", "descEN",
                "descUA", 10L, 10L, 10L);
        expectedSurgery.setId(10L);
        expectedSurgery.setCreateTime(localDateTime);

        expectedEmployeeMedicamentBean = new EmployeeMedicamentBean(expectedMedicament);
        expectedEmployeeMedicamentBean.setPatientBirthday(localDate);
        expectedEmployeeMedicamentBean.setPatientNameEN("patientNameEN");
        expectedEmployeeMedicamentBean.setPatientNameUA("patientNameUA");
        expectedEmployeeMedicamentBean.setPatientSurnameEN("patientSurnameEN");
        expectedEmployeeMedicamentBean.setPatientSurnameUA("patientSurnameUA");
        expectedEmployeeMedicamentBean.setCreatedByNameEN("createdByNameEN");
        expectedEmployeeMedicamentBean.setCreatedByNameUA("createdByNameUA");
        expectedEmployeeMedicamentBean.setCreatedBySurnameEN("createdBySurnameEN");
        expectedEmployeeMedicamentBean.setCreatedBySurnameUA("createdBySurnameUA");

        expectedEmployeeSurgeryBean = new EmployeeSurgeryBean(expectedSurgery);
        expectedEmployeeSurgeryBean.setPatientBirthday(localDate);
        expectedEmployeeSurgeryBean.setPatientNameEN("patientNameEN");
        expectedEmployeeSurgeryBean.setPatientNameUA("patientNameUA");
        expectedEmployeeSurgeryBean.setPatientSurnameEN("patientSurnameEN");
        expectedEmployeeSurgeryBean.setPatientSurnameUA("patientSurnameUA");
        expectedEmployeeSurgeryBean.setCreatedByNameEN("createdByNameEN");
        expectedEmployeeSurgeryBean.setCreatedByNameUA("createdByNameUA");
        expectedEmployeeSurgeryBean.setCreatedBySurnameEN("createdBySurnameEN");
        expectedEmployeeSurgeryBean.setCreatedBySurnameUA("createdBySurnameUA");

        expectedEmployeeProcedureBean = new EmployeeProcedureBean(expectedProcedure);
        expectedEmployeeProcedureBean.setPatientBirthday(localDate);
        expectedEmployeeProcedureBean.setPatientNameEN("patientNameEN");
        expectedEmployeeProcedureBean.setPatientNameUA("patientNameUA");
        expectedEmployeeProcedureBean.setPatientSurnameEN("patientSurnameEN");
        expectedEmployeeProcedureBean.setPatientSurnameUA("patientSurnameUA");
        expectedEmployeeProcedureBean.setCreatedByNameEN("createdByNameEN");
        expectedEmployeeProcedureBean.setCreatedByNameUA("createdByNameUA");
        expectedEmployeeProcedureBean.setCreatedBySurnameEN("createdBySurnameEN");
        expectedEmployeeProcedureBean.setCreatedBySurnameUA("createdBySurnameUA");

        expectedPatientMedicamentBean = new PatientMedicamentBean(expectedMedicament);
        expectedPatientMedicamentBean.setEmpNameEN("empNameEN");
        expectedPatientMedicamentBean.setEmpNameUA("empNameUA");
        expectedPatientMedicamentBean.setEmpSurnameEN("empSurnameEN");
        expectedPatientMedicamentBean.setEmpSurnameUA("empSurnameUA");
        expectedPatientMedicamentBean.setCreatedByNameEN("createdByNameEN");
        expectedPatientMedicamentBean.setCreatedByNameUA("createdByNameUA");
        expectedPatientMedicamentBean.setCreatedBySurnameEN("createdBySurnameEN");
        expectedPatientMedicamentBean.setCreatedBySurnameUA("createdBySurnameUA");

        expectedPatientProcedureBean = new PatientProcedureBean(expectedProcedure);
        expectedPatientProcedureBean.setEmpNameEN("empNameEN");
        expectedPatientProcedureBean.setEmpNameUA("empNameUA");
        expectedPatientProcedureBean.setEmpSurnameEN("empSurnameEN");
        expectedPatientProcedureBean.setEmpSurnameUA("empSurnameUA");
        expectedPatientProcedureBean.setCreatedByNameEN("createdByNameEN");
        expectedPatientProcedureBean.setCreatedByNameUA("createdByNameUA");
        expectedPatientProcedureBean.setCreatedBySurnameEN("createdBySurnameEN");
        expectedPatientProcedureBean.setCreatedBySurnameUA("createdBySurnameUA");

        expectedPatientSurgeryBean = new PatientSurgeryBean(expectedSurgery);
        expectedPatientSurgeryBean.setEmpNameEN("empNameEN");
        expectedPatientSurgeryBean.setEmpNameUA("empNameUA");
        expectedPatientSurgeryBean.setEmpSurnameEN("empSurnameEN");
        expectedPatientSurgeryBean.setEmpSurnameUA("empSurnameUA");
        expectedPatientSurgeryBean.setCreatedByNameEN("createdByNameEN");
        expectedPatientSurgeryBean.setCreatedByNameUA("createdByNameUA");
        expectedPatientSurgeryBean.setCreatedBySurnameEN("createdBySurnameEN");
        expectedPatientSurgeryBean.setCreatedBySurnameUA("createdBySurnameUA");
    }

    @Before
    public void setUp() throws SQLException {
        when(rs.getLong(MySQLFields.ID)).thenReturn(10L);
        when(rs.getInt(MySQLFields.ID)).thenReturn(10);
        when(rs.getLong(MySQLFields.CREATED_BY)).thenReturn(10L);
        when(rs.getLong(MySQLFields.SERVED_BY)).thenReturn(10L);
        when(rs.getLong(MySQLFields.UPDATED_BY)).thenReturn(10L);
        when(rs.getTimestamp(MySQLFields.CREATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getTimestamp(MySQLFields.UPDATE_TIME)).thenReturn(Timestamp.valueOf(localDateTime));
        when(rs.getString(MySQLFields.NAME_EN)).thenReturn("nameEN");
        when(rs.getString(MySQLFields.NAME_UA)).thenReturn("nameUA");
        when(rs.getString(MySQLFields.DESCRIPTION_EN)).thenReturn("descEN");
        when(rs.getString(MySQLFields.DESCRIPTION_UA)).thenReturn("descUA");
        when(rs.getBoolean(MySQLFields.END)).thenReturn(false);
        when(rs.getLong(MySQLFields.MEDICAL_CARD_ID)).thenReturn(10L);

        when(rs.getString(MySQLFields.ACCOUNT_LOGIN)).thenReturn("Login");
        when(rs.getString(MySQLFields.ACCOUNT_PASSWORD)).thenReturn("Password");
        when(rs.getString(MySQLFields.ACCOUNT_EMAIL)).thenReturn("Email");
        when(rs.getBoolean(MySQLFields.ACCOUNT_LOCKED)).thenReturn(false);
        when(rs.getInt(MySQLFields.ACCOUNT_ROLE_ID)).thenReturn(1);
        when(rs.getInt(MySQLFields.ACCOUNT_LOCALE_ID)).thenReturn(1);

        when(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_EN)).thenReturn("surnameEN");
        when(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_UA)).thenReturn("surnameUA");

        when(rs.getBoolean(MySQLFields.MEDICAL_CARD_IS_DISCHARGED)).thenReturn(false);
        when(rs.getLong(MySQLFields.MEDICAL_CARD_PATIENT_ID)).thenReturn(10L);
        when(rs.getLong(MySQLFields.MEDICAL_CARD_DOCTOR_ID)).thenReturn(10L);

        when(rs.getInt(MySQLFields.DOCTOR_SPECIALIZATION_ID)).thenReturn(10);

        when(rs.getDate(MySQLFields.BIRTHDAY)).thenReturn(Date.valueOf(localDate));

        when(rs.getDate(MySQLFields.PATIENT_BIRTHDAY)).thenReturn(Date.valueOf(localDate));
        when(rs.getString(MySQLFields.PATIENT_NAME_EN)).thenReturn("patientNameEN");
        when(rs.getString(MySQLFields.PATIENT_NAME_UA)).thenReturn("patientNameUA");
        when(rs.getString(MySQLFields.PATIENT_SURNAME_EN)).thenReturn("patientSurnameEN");
        when(rs.getString(MySQLFields.PATIENT_SURNAME_UA)).thenReturn("patientSurnameUA");
        when(rs.getString(MySQLFields.CREATED_BY_NAME_EN)).thenReturn("createdByNameEN");
        when(rs.getString(MySQLFields.CREATED_BY_NAME_UA)).thenReturn("createdByNameUA");
        when(rs.getString(MySQLFields.CREATED_BY_SURNAME_EN)).thenReturn("createdBySurnameEN");
        when(rs.getString(MySQLFields.CREATED_BY_SURNAME_UA)).thenReturn("createdBySurnameUA");
        when(rs.getString(MySQLFields.EMPLOYEE_NAME_EN)).thenReturn("empNameEN");
        when(rs.getString(MySQLFields.EMPLOYEE_NAME_UA)).thenReturn("empNameUA");
        when(rs.getString(MySQLFields.EMPLOYEE_SURNAME_EN)).thenReturn("empSurnameEN");
        when(rs.getString(MySQLFields.EMPLOYEE_SURNAME_UA)).thenReturn("empSurnameUA");
    }

    @Test
    public void mapAccountTest() throws SQLException {
        Account actual = ObjectMapper.mapAccount(rs);
        assertEquals(expectedAccount, actual);
    }

    @Test
    public void mapAccountDetailsTest() throws SQLException {
        AccountDetails actual = ObjectMapper.mapAccountDetails(rs);
        assertEquals(expectedAccountDetails, actual);
    }

    @Test
    public void mapMedicalCardTest() throws SQLException {
        MedicalCard actual = ObjectMapper.mapMedicalCard(rs);
        assertEquals(expectedMedicalCard, actual);
    }

    @Test
    public void mapDoctorTest() throws SQLException {
        Doctor actual = ObjectMapper.mapDoctor(rs);
        assertEquals(expectedDoctor, actual);
    }

    @Test
    public void mapPatientTest() throws SQLException {
        Patient actual = ObjectMapper.mapPatient(rs);
        assertEquals(expectedPatient, actual);
    }

    @Test
    public void mapSpecializationTest() throws SQLException {
        Specialization actual = ObjectMapper.mapSpecialization(rs);
        assertEquals(expectedSpecialization, actual);
    }

    @Test
    public void mapMedicamentTest() throws SQLException {
        Medicament actual = ObjectMapper.mapMedicament(rs);
        assertEquals(expectedMedicament, actual);
    }

    @Test
    public void mapProcedureTest() throws SQLException {
        Procedure actual = ObjectMapper.mapProcedure(rs);
        assertEquals(expectedProcedure, actual);
    }

    @Test
    public void mapSurgeryTest() throws SQLException {
        Surgery actual = ObjectMapper.mapSurgery(rs);
        assertEquals(expectedSurgery, actual);
    }

    @Test
    public void mapEmployeeMedicamentBeanTest() throws SQLException {
        EmployeeMedicamentBean actualEmployeeMedicamentBean = ObjectMapper.mapEmployeeMedicamentBean(rs);
        assertEquals(expectedEmployeeMedicamentBean, actualEmployeeMedicamentBean);
    }

    @Test
    public void mapEmployeeSurgeryBeanTest() throws SQLException {
        EmployeeSurgeryBean actual = ObjectMapper.mapEmployeeSurgeryBean(rs);
        assertEquals(expectedEmployeeSurgeryBean, actual);
    }

    @Test
    public void mapEmployeeProcedureBeanTest() throws SQLException {
        EmployeeProcedureBean actual = ObjectMapper.mapEmployeeProcedureBean(rs);
        assertEquals(expectedEmployeeProcedureBean, actual);
    }

    @Test
    public void mapPatientMedicamentBeanTest() throws SQLException {
        PatientMedicamentBean actual = ObjectMapper.mapPatientMedicamentBean(rs);
        assertEquals(expectedPatientMedicamentBean, actual);
    }

    @Test
    public void mapPatientProcedureBeanTest() throws SQLException {
        PatientProcedureBean actual = ObjectMapper.mapPatientProcedureBean(rs);
        assertEquals(expectedPatientProcedureBean, actual);
    }

    @Test
    public void mapPatientSurgeryBeanTest() throws SQLException {
        PatientSurgeryBean actual = ObjectMapper.mapPatientSurgeryBean(rs);
        assertEquals(expectedPatientSurgeryBean, actual);
    }
}