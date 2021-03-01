package ua.com.dao.impl;

import ua.com.bean.*;
import ua.com.constant.MySQLFields;
import ua.com.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

class ObjectMapper {
    /**
     * Extracts a user from the result set.
     *
     * @param rs which should be mapped
     * @return an Account entity
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is called on a closed result set
     */
    static Account mapAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong(MySQLFields.ID));
        account.setLogin(rs.getString(MySQLFields.ACCOUNT_LOGIN));
        account.setPassword(rs.getString(MySQLFields.ACCOUNT_PASSWORD));
        account.setEmail(rs.getString(MySQLFields.ACCOUNT_EMAIL));
        account.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        account.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        account.setLocked(rs.getBoolean(MySQLFields.ACCOUNT_LOCKED));
        account.setUpdatedBy(rs.getLong(MySQLFields.UPDATED_BY));
        account.setRoleId(rs.getInt(MySQLFields.ACCOUNT_ROLE_ID));
        account.setLocaleId(rs.getInt(MySQLFields.ACCOUNT_LOCALE_ID));
        return account;
    }

    /**
     * Extracts accountDetails from the result set.
     *
     * @param rs which should be mapped
     * @return AccountDetails entity
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is called on a closed result set
     */
    static AccountDetails mapAccountDetails(ResultSet rs) throws SQLException {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(rs.getLong(MySQLFields.ID));
        accountDetails.setNameEN(rs.getString(MySQLFields.NAME_EN));
        accountDetails.setSurnameEN(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_EN));
        accountDetails.setNameUA(rs.getString(MySQLFields.NAME_UA));
        accountDetails.setSurnameUA(rs.getString(MySQLFields.ACCOUNT_DETAILS_SURNAME_UA));
        return accountDetails;
    }

    static MedicalCard mapMedicalCard(ResultSet rs) throws SQLException {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(rs.getLong(MySQLFields.ID));
        medicalCard.setDischarged(rs.getBoolean(MySQLFields.MEDICAL_CARD_IS_DISCHARGED));
        medicalCard.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        medicalCard.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        medicalCard.setUpdatedBy(rs.getLong(MySQLFields.UPDATED_BY));
        medicalCard.setPatientId(rs.getLong(MySQLFields.MEDICAL_CARD_PATIENT_ID));
        medicalCard.setDoctorId(rs.getLong(MySQLFields.MEDICAL_CARD_DOCTOR_ID));
        return medicalCard;
    }

    static Doctor mapDoctor(ResultSet rs) throws SQLException{
        Doctor doctor = new Doctor();
        doctor.setId(rs.getLong(MySQLFields.ID));
        doctor.setSpecializationId(rs.getInt(MySQLFields.DOCTOR_SPECIALIZATION_ID));
        return doctor;
    }

    static Patient mapPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getLong(MySQLFields.ID));
        patient.setBirthday(rs.getDate(MySQLFields.BIRTHDAY).toLocalDate());
        return patient;
    }

    static Specialization mapSpecialization(ResultSet rs) throws SQLException {
        Specialization specialization = new Specialization();
        specialization.setId(rs.getInt(MySQLFields.ID));
        specialization.setNameEN(rs.getString(MySQLFields.NAME_EN));
        specialization.setNameUA(rs.getString(MySQLFields.NAME_UA));
        specialization.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        specialization.setUpdatedBy(rs.getLong(MySQLFields.UPDATED_BY));
        return specialization;
    }

    static Medicament mapMedicament(ResultSet rs) throws SQLException {
        return (Medicament) mapAssignment(rs, new Medicament());
    }

    static Procedure mapProcedure(ResultSet rs) throws SQLException {
        return (Procedure) mapAssignment(rs, new Procedure());
    }

    static Surgery mapSurgery(ResultSet rs) throws SQLException {
        return (Surgery) mapAssignment(rs, new Surgery());
    }

    static EmployeeMedicamentBean mapEmployeeMedicamentBean(ResultSet rs) throws SQLException {
        EmployeeMedicamentBean bean = new EmployeeMedicamentBean(mapAssignment(rs, new Medicament()));
        return (EmployeeMedicamentBean) mapEmployeeAssignmentBean(rs, bean);
    }

    static EmployeeSurgeryBean mapEmployeeSurgeryBean(ResultSet rs) throws SQLException {
        EmployeeSurgeryBean bean = new EmployeeSurgeryBean(mapAssignment(rs, new Surgery()));
        return (EmployeeSurgeryBean) mapEmployeeAssignmentBean(rs, bean);
    }

    static EmployeeProcedureBean mapEmployeeProcedureBean(ResultSet rs) throws SQLException {
        EmployeeProcedureBean bean = new EmployeeProcedureBean(mapAssignment(rs, new Procedure()));
        return (EmployeeProcedureBean) mapEmployeeAssignmentBean(rs, bean);
    }

    static PatientMedicamentBean mapPatientMedicamentBean(ResultSet rs) throws SQLException {
        PatientMedicamentBean bean = new PatientMedicamentBean(mapAssignment(rs, new Medicament()));
        return (PatientMedicamentBean) mapPatientAssignmentBean(rs, bean);
    }

    static PatientProcedureBean mapPatientProcedureBean(ResultSet rs) throws SQLException {
        PatientProcedureBean bean = new PatientProcedureBean(mapAssignment(rs, new Procedure()));
        return (PatientProcedureBean) mapPatientAssignmentBean(rs, bean);
    }

    static PatientSurgeryBean mapPatientSurgeryBean(ResultSet rs) throws SQLException {
        PatientSurgeryBean bean = new PatientSurgeryBean(mapAssignment(rs, new Surgery()));
        return (PatientSurgeryBean) mapPatientAssignmentBean(rs, bean);
    }

    static NurseAccDetailsBean mapNurseAccDetailsBean(ResultSet rs) throws SQLException {
        Account account = mapAccount(rs);
        AccountDetails accountDetails = mapAccountDetails(rs);
        return new NurseAccDetailsBean(account, accountDetails);
    }

    static DiagnosisDoctorBean mapDiagnosisDoctorBean(ResultSet rs) throws SQLException {
        AccountDetails doctorAccDetails = mapDoctorAccDetails(rs);
        Specialization specialization = mapSpecializationName(rs);
        DiagnosisDoctorBean bean = new DiagnosisDoctorBean(doctorAccDetails, specialization);
        bean.setId(rs.getLong(MySQLFields.ID));
        bean.setNameEN(rs.getString(MySQLFields.NAME_EN));
        bean.setNameUA(rs.getString(MySQLFields.NAME_UA));
        bean.setDescriptionEN(rs.getString(MySQLFields.DESCRIPTION_EN));
        bean.setDescriptionUA(rs.getString(MySQLFields.DESCRIPTION_UA));
        bean.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        bean.setCreatedBy(rs.getLong(MySQLFields.CREATED_BY));
        bean.setMedicalCardId(rs.getLong(MySQLFields.MEDICAL_CARD_ID));
        return bean;
    }

    static DoctorAccDetailsBean mapDoctorAccDetailsBean(ResultSet rs) throws SQLException {
        AccountDetails accountDetails = mapAccountDetails(rs);
        DoctorAccDetailsBean bean = new DoctorAccDetailsBean(accountDetails);
        bean.setId(rs.getLong(MySQLFields.ID));
        bean.setSpecializationId(rs.getInt(MySQLFields.DOCTOR_SPECIALIZATION_ID));
        return bean;
    }

    static DoctorAccountBean mapDoctorAccountBean(ResultSet rs) throws SQLException {
        DoctorAccDetailsBean doctorAccDetailsBean = mapDoctorAccDetailsBean(rs);
        AccountDetails updatedByAccDetails = mapUpdatedByAccDetails(rs);
        Specialization specialization = mapSpecializationName(rs);
        DoctorAccountBean bean = new DoctorAccountBean(doctorAccDetailsBean, updatedByAccDetails, specialization);
        bean.setEmail(rs.getString(MySQLFields.ACCOUNT_EMAIL));
        bean.setLocked(rs.getBoolean(MySQLFields.ACCOUNT_LOCKED));
        bean.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        bean.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        bean.setPatientCount(rs.getInt(MySQLFields.DOCTOR_PATIENT_COUNT));
        return bean;
    }

    static MedCardDoctorBean mapMedCardDoctorBean(ResultSet rs) throws SQLException {
        MedicalCard medicalCard = mapMedicalCard(rs);
        AccountDetails doctorAccDetails = mapDoctorAccDetails(rs);
        AccountDetails updatedByAccDetails = mapUpdatedByAccDetails(rs);
        Specialization specialization = mapSpecializationName(rs);
        return new MedCardDoctorBean(medicalCard, doctorAccDetails, updatedByAccDetails, specialization);
    }

    static MedCardPatientBean mapMedCardPatientBean(ResultSet rs) throws SQLException {
        MedicalCard medicalCard = mapMedicalCard(rs);
        MedCardPatientBean bean = new MedCardPatientBean(medicalCard);
        bean.setPatientNameEN(rs.getString(MySQLFields.PATIENT_NAME_EN));
        bean.setPatientSurnameEN(rs.getString(MySQLFields.PATIENT_SURNAME_EN));
        bean.setPatientNameUA(rs.getString(MySQLFields.PATIENT_NAME_UA));
        bean.setPatientSurnameUA(rs.getString(MySQLFields.PATIENT_SURNAME_UA));
        bean.setPatientBirthday(rs.getDate(MySQLFields.PATIENT_BIRTHDAY).toLocalDate());
        return bean;
    }


    static MedicamentDoctorBean mapMedicamentDoctorBean(ResultSet rs) throws SQLException {
        Medicament medicament = mapMedicament(rs);
        AccountDetails doctorAccDetails = mapDoctorAccDetails(rs);
        Specialization specialization = mapSpecializationName(rs);
        MedicamentDoctorBean bean = new MedicamentDoctorBean(medicament, doctorAccDetails, specialization);
        bean.setServedByNameEN(rs.getString(MySQLFields.SERVED_BY_NAME_EN));
        bean.setServedBySurnameEN(rs.getString(MySQLFields.SERVED_BY_SURNAME_EN));
        bean.setServedByNameUA(rs.getString(MySQLFields.SERVED_BY_NAME_UA));
        bean.setServedBySurnameUA(rs.getString(MySQLFields.SERVED_BY_SURNAME_UA));
        return bean;
    }


    static PatientAccountBean mapPatientAccountBean(ResultSet rs) throws SQLException {
        AccountDetails accountDetails = mapAccountDetails(rs);
        AccountDetails updatedByAccDetails = mapUpdatedByAccDetails(rs);
        PatientAccountBean bean = new PatientAccountBean(accountDetails, updatedByAccDetails);
        bean.setId(rs.getLong(MySQLFields.ID));
        bean.setEmail(rs.getString(MySQLFields.ACCOUNT_EMAIL));
        bean.setBirthday(rs.getDate(MySQLFields.BIRTHDAY).toLocalDate());
        bean.setLocked(rs.getBoolean(MySQLFields.ACCOUNT_LOCKED));
        bean.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        bean.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        return bean;
    }


    static ProcedureDoctorBean mapProcedureDoctorBean(ResultSet rs) throws SQLException {
        Procedure procedure = mapProcedure(rs);
        AccountDetails doctorAccDetails = mapDoctorAccDetails(rs);
        Specialization specialization = mapSpecializationName(rs);
        ProcedureDoctorBean bean = new ProcedureDoctorBean(procedure, doctorAccDetails, specialization);
        bean.setServedByNameEN(rs.getString(MySQLFields.SERVED_BY_NAME_EN));
        bean.setServedBySurnameEN(rs.getString(MySQLFields.SERVED_BY_SURNAME_EN));
        bean.setServedByNameUA(rs.getString(MySQLFields.SERVED_BY_NAME_UA));
        bean.setServedBySurnameUA(rs.getString(MySQLFields.SERVED_BY_SURNAME_UA));
        return bean;
    }


    static SpecializationAccountDetailsBean mapSpecAccDetailsBean(ResultSet rs) throws SQLException {
        AccountDetails updatedByAccDetails = mapUpdatedByAccDetails(rs);
        SpecializationAccountDetailsBean bean = new SpecializationAccountDetailsBean(updatedByAccDetails);
        bean.setId(rs.getInt(MySQLFields.ID));
        bean.setNameEN(rs.getString(MySQLFields.NAME_EN));
        bean.setNameUA(rs.getString(MySQLFields.NAME_UA));
        bean.setUpdateTime(rs.getTimestamp(MySQLFields.UPDATE_TIME).toLocalDateTime());
        return bean;
    }


    static SurgeryDoctorBean mapSurgeryDoctorBean(ResultSet rs) throws SQLException {
        Surgery surgery = mapSurgery(rs);
        AccountDetails doctorAccDetails = mapDoctorAccDetails(rs);
        Specialization specialization = mapSpecializationName(rs);
        SurgeryDoctorBean bean = new SurgeryDoctorBean(surgery, doctorAccDetails, specialization);
        bean.setServedByNameEN(rs.getString(MySQLFields.SERVED_BY_NAME_EN));
        bean.setServedBySurnameEN(rs.getString(MySQLFields.SERVED_BY_SURNAME_EN));
        bean.setServedByNameUA(rs.getString(MySQLFields.SERVED_BY_NAME_UA));
        bean.setServedBySurnameUA(rs.getString(MySQLFields.SERVED_BY_SURNAME_UA));
        return bean;
    }

    private static AccountDetails mapDoctorAccDetails(ResultSet rs) throws SQLException {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setNameEN(rs.getString(MySQLFields.DOCTOR_NAME_EN));
        accountDetails.setSurnameEN(rs.getString(MySQLFields.DOCTOR_SURNAME_EN));
        accountDetails.setNameUA(rs.getString(MySQLFields.DOCTOR_NAME_UA));
        accountDetails.setSurnameUA(rs.getString(MySQLFields.DOCTOR_SURNAME_UA));
        return accountDetails;
    }

    private static AccountDetails mapUpdatedByAccDetails(ResultSet rs) throws SQLException {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setNameEN(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_EN));
        accountDetails.setSurnameEN(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_EN));
        accountDetails.setNameUA(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_NAME_UA));
        accountDetails.setSurnameUA(rs.getString(MySQLFields.ACC_DETAILS_UPDATE_BY_SURNAME_UA));
        return accountDetails;
    }

    private static Specialization mapSpecializationName(ResultSet rs) throws SQLException {
        Specialization specialization = new Specialization();
        specialization.setNameEN(rs.getString(MySQLFields.SPECIALIZATION_NAME_EN));
        specialization.setNameUA(rs.getString(MySQLFields.SPECIALIZATION_NAME_UA));
        return specialization;
    }

    private static Assignment mapAssignment(ResultSet rs, Assignment assignment) throws SQLException {
        assignment.setId(rs.getLong(MySQLFields.ID));
        assignment.setNameEN(rs.getString(MySQLFields.NAME_EN));
        assignment.setNameUA(rs.getString(MySQLFields.NAME_UA));
        assignment.setDescriptionEN(rs.getString(MySQLFields.DESCRIPTION_EN));
        assignment.setDescriptionUA(rs.getString(MySQLFields.DESCRIPTION_UA));
        assignment.setEnd(rs.getBoolean(MySQLFields.END));
        assignment.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        assignment.setCreatedBy(rs.getLong(MySQLFields.CREATED_BY));
        assignment.setServedBy(rs.getLong(MySQLFields.SERVED_BY));
        assignment.setMedicalCardId(rs.getLong(MySQLFields.MEDICAL_CARD_ID));
        return assignment;
    }

    private static EmployeeAssignmentBean mapEmployeeAssignmentBean(ResultSet rs, EmployeeAssignmentBean bean) throws SQLException {
        bean.setPatientBirthday(rs.getDate(MySQLFields.PATIENT_BIRTHDAY).toLocalDate());
        bean.setPatientNameEN(rs.getString(MySQLFields.PATIENT_NAME_EN));
        bean.setPatientSurnameEN(rs.getString(MySQLFields.PATIENT_SURNAME_EN));
        bean.setPatientNameUA(rs.getString(MySQLFields.PATIENT_NAME_UA));
        bean.setPatientSurnameUA(rs.getString(MySQLFields.PATIENT_SURNAME_UA));
        bean.setCreatedByNameEN(rs.getString(MySQLFields.CREATED_BY_NAME_EN));
        bean.setCreatedBySurnameEN(rs.getString(MySQLFields.CREATED_BY_SURNAME_EN));
        bean.setCreatedByNameUA(rs.getString(MySQLFields.CREATED_BY_NAME_UA));
        bean.setCreatedBySurnameUA(rs.getString(MySQLFields.CREATED_BY_SURNAME_UA));
        return bean;
    }

    private static PatientAssignmentBean mapPatientAssignmentBean(ResultSet rs, PatientAssignmentBean bean) throws SQLException {
        Specialization specialization = mapSpecializationName(rs);
        bean.setSpecialization(specialization);
        bean.setEmpNameEN(rs.getString(MySQLFields.EMPLOYEE_NAME_EN));
        bean.setEmpSurnameEN(rs.getString(MySQLFields.EMPLOYEE_SURNAME_EN));
        bean.setEmpNameUA(rs.getString(MySQLFields.EMPLOYEE_NAME_UA));
        bean.setEmpSurnameUA(rs.getString(MySQLFields.EMPLOYEE_SURNAME_UA));
        bean.setCreatedByNameEN(rs.getString(MySQLFields.CREATED_BY_NAME_EN));
        bean.setCreatedBySurnameEN(rs.getString(MySQLFields.CREATED_BY_SURNAME_EN));
        bean.setCreatedByNameUA(rs.getString(MySQLFields.CREATED_BY_NAME_UA));
        bean.setCreatedBySurnameUA(rs.getString(MySQLFields.CREATED_BY_SURNAME_UA));
        return bean;
    }
}