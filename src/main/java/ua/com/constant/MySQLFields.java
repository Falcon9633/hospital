package ua.com.constant;

/**
 * Holds fields from the database.
 *
 * @author Orest Dmyterko
 */
public class MySQLFields {
    // common
    public static final String ID = "id";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";
    public static final String CREATED_BY = "created_by";
    public static final String UPDATED_BY = "updated_by";
    public static final String NAME_EN = "name_EN";
    public static final String NAME_UA = "name_UA";
    public static final String NUMBER = "number";
    public static final String DESCRIPTION_EN = "description_EN";
    public static final String DESCRIPTION_UA = "description_UA";
    public static final String MEDICAL_CARD_ID = "medical_card_id";
    public static final String END = "end";
    public static final String SERVED_BY = "served_by";
    public static final String SERVED_BY_NAME_EN = "served_by_name_EN";
    public static final String SERVED_BY_SURNAME_EN = "served_by_surname_EN";
    public static final String SERVED_BY_NAME_UA = "served_by_name_UA";
    public static final String SERVED_BY_SURNAME_UA = "served_by_surname_UA";
    public static final String PATIENT_NAME_EN = "patient_name_EN";
    public static final String PATIENT_SURNAME_EN = "patient_surname_EN";
    public static final String PATIENT_NAME_UA = "patient_name_UA";
    public static final String PATIENT_SURNAME_UA = "patient_surname_UA";
    public static final String PATIENT_BIRTHDAY = "patient_birthday";
    public static final String CREATED_BY_NAME_EN = "created_by_name_EN";
    public static final String CREATED_BY_SURNAME_EN = "created_by_surname_EN";
    public static final String CREATED_BY_NAME_UA = "created_by_name_UA";
    public static final String CREATED_BY_SURNAME_UA = "created_by_surname_UA";
    public static final String DOCTOR_NAME_EN = "doctor_name_EN";
    public static final String DOCTOR_SURNAME_EN = "doctor_surname_EN";
    public static final String DOCTOR_NAME_UA = "doctor_name_UA";
    public static final String DOCTOR_SURNAME_UA = "doctor_surname_UA";
    public static final String EMPLOYEE_NAME_EN = "emp_name_EN";
    public static final String EMPLOYEE_SURNAME_EN = "emp_surname_EN";
    public static final String EMPLOYEE_NAME_UA = "emp_name_UA";
    public static final String EMPLOYEE_SURNAME_UA = "emp_surname_UA";
    public static final String SPECIALIZATION_NAME_EN = "specialization_name_EN";
    public static final String SPECIALIZATION_NAME_UA = "specialization_name_UA";

    // account
    public static final String ACCOUNT_LOGIN = "login";
    public static final String ACCOUNT_PASSWORD = "password";
    public static final String ACCOUNT_LOCKED = "locked";
    public static final String ACCOUNT_ROLE_ID = "role_id";
    public static final String ACCOUNT_LOCALE_ID = "locale_id";
    public static final String ACCOUNT_EMAIL = "email";

    // account_details
    public static final String ACCOUNT_DETAILS_SURNAME_EN = "surname_EN";
    public static final String ACCOUNT_DETAILS_SURNAME_UA = "surname_UA";
    public static final String ACC_DETAILS_UPDATE_BY_NAME_EN = "updatedBy_name_EN";
    public static final String ACC_DETAILS_UPDATE_BY_SURNAME_EN = "updatedBy_surname_EN";
    public static final String ACC_DETAILS_UPDATE_BY_NAME_UA = "updatedBy_name_UA";
    public static final String ACC_DETAILS_UPDATE_BY_SURNAME_UA = "updatedBy_surname_UA";

    // patient
    public static final String BIRTHDAY = "birthday";

    //medical_card
    public static final String MEDICAL_CARD_IS_DISCHARGED = "is_discharged";
    public static final String MEDICAL_CARD_PATIENT_ID = "patient_id";
    public static final String MEDICAL_CARD_DOCTOR_ID = "doctor_id";

    // doctor
    public static final String DOCTOR_SPECIALIZATION_ID = "specialization_id";
    public static final String DOCTOR_PATIENT_COUNT = "patient_count";
}
