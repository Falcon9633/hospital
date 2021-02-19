package ua.com.constant;

/**
 * Holds MySql queries.
 *
 * @author OrestDmyterko
 */
public class MySQLQuery {
    // account
    public static final String FIND_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id=?";
    public static final String FIND_ACCOUNT_BY_ID_AND_ROLE = "SELECT * FROM account WHERE id=? AND role_id=?";
    public static final String FIND_ACCOUNT_BY_LOGIN = "SELECT * FROM account WHERE login=?";
    public static final String INSERT_ACCOUNT =
            "INSERT INTO account(login, password, email, updated_by, role_id) VALUES(?, ?, ?, ?, ?)";
    public static final String UPDATE_ACCOUNT = "UPDATE account SET login=?, password=?, email=?, locked=?, " +
            "updated_by=?, role_id=?, locale_id=? WHERE id=?";
    // account_details
    public static final String FIND_ACCOUNT_DETAILS_BY_ID = "SELECT * FROM account_details WHERE id=?";
    public static final String INSERT_ACCOUNT_DETAILS =
            "INSERT INTO account_details(id, name_EN, surname_EN, name_UA, surname_UA) VALUES(?, ?, ?, ?, ?)";
    public static final String UPDATE_ACCOUNT_DETAILS = "UPDATE account_details SET name_EN=?, surname_EN=?, " +
            "name_UA=?, surname_UA=? WHERE id=?";
    // doctor
    public static final String FIND_DOCTOR_BY_ID = "SELECT * FROM doctor WHERE id=?";
    public static final String INSERT_DOCTOR = "INSERT INTO doctor(id, specialization_id) VALUES(?, ?)";
    public static final String UPDATE_DOCTOR = "UPDATE doctor SET specialization_id=? WHERE id=?";
    // doctor_account_details bean
    public static final String FIND_ALL_DOCTOR_ACCOUNT_DETAILS_BEANS =
            "SELECT * FROM doctor d\n" +
            "JOIN account_details ad ON d.id=ad.id";
    // doctor_account bean
    public static final String FIND_ALL_DOCTOR_ACCOUNT_BEANS =
            "SELECT\n" +
                "d.*,\n" +
                "ad.name_EN,\n" +
                "ad.surname_EN,\n" +
                "ad.name_UA,\n" +
                "ad.surname_UA,\n" +
                "a.email,\n" +
                "a.locked,\n" +
                "a.create_time,\n" +
                "a.update_time,\n" +
                "updatedBy.name_EN updatedBy_name_EN,\n" +
                "updatedBy.surname_EN updatedBy_surname_EN,\n" +
                "updatedBy.name_UA updatedBy_name_UA,\n" +
                "updatedBy.surname_UA updatedBy_surname_UA,\n" +
                "(SELECT count(patient_id) from medical_card mc where mc.doctor_id=d.id AND mc.is_discharged=0) patient_count,\n" +
                "ds.name_EN specialization_name_EN,\n" +
                "ds.name_UA specialization_name_UA\n" +
            "FROM\n" +
                "doctor d\n" +
            "JOIN\n" +
                "account a ON d.id=a.id\n" +
            "JOIN\n" +
                "account_details ad ON d.id=ad.id\n" +
            "JOIN\n" +
                "account_details updatedBy ON a.updated_by=updatedBy.id\n" +
            "JOIN\n" +
                "specialization ds ON d.specialization_id=ds.id";
    // patient
    public static final String FIND_PATIENT_BY_ID = "SELECT * FROM patient WHERE id=?";
    public static final String INSERT_PATIENT = "INSERT INTO patient(id, birthday) VALUES(?, ?)";
    public static final String GET_NUMBER_OF_ROWS_PATIENT = "SELECT COUNT(id) number FROM patient";
    public static final String UPDATE_PATIENT = "UPDATE patient SET birthday=? WHERE id=?";
    // patient_account bean
    public static final String FIND_ALL_PATIENT_ACCOUNT_BEANS =
            "SELECT\n" +
                "p.*,\n" +
                "ad.name_EN,\n" +
                "ad.surname_EN,\n" +
                "ad.name_UA,\n" +
                "ad.surname_UA,\n" +
                "a.email,\n" +
                "a.locked,\n" +
                "a.create_time,\n" +
                "a.update_time,\n" +
                "updatedBy.name_EN updatedBy_name_EN,\n" +
                "updatedBy.surname_EN updatedBy_surname_EN,\n" +
                "updatedBy.name_UA updatedBy_name_UA,\n" +
                "updatedBy.surname_UA updatedBy_surname_UA\n" +
            "FROM\n" +
                "patient p\n" +
            "JOIN\n" +
                "account a ON p.id=a.id\n" +
            "JOIN\n" +
                "account_details ad ON p.id=ad.id\n" +
            "JOIN\n" +
                "account_details updatedBy ON a.updated_by=updatedBy.id\n" +
            "ORDER BY %s %s\n" +
            "LIMIT ?, ?";
    // specialization
    public static final String FIND_ALL_SPECIALIZATIONS = "SELECT * FROM specialization";
    public static final String FIND_SPECIALIZATION_BY_ID = "SELECT * FROM specialization WHERE id=?";
    public static final String FIND_SPECIALIZATION_BY_NAME = "SELECT * FROM specialization WHERE name_en=? OR name_ua=?";
    public static final String INSERT_SPECIALIZATION =
            "INSERT INTO specialization(name_EN, name_UA, updated_by) VALUES(?, ?, ?)";
    public static final String UPDATE_SPECIALIZATION = "UPDATE specialization SET name_EN=?, name_UA=?, updated_by=? WHERE id=?";
    // specialization_account_details bean
    public static final String FIND_ALL_SPECIALIZATION_ACC_DETAILS_BEANS =
            "SELECT\n" +
                "s.*,\n" +
                "a.name_EN updatedBy_name_EN,\n" +
                "a.surname_EN updatedBy_surname_EN,\n" +
                "a.name_UA updatedBy_name_UA,\n" +
                "a.surname_UA updatedBy_surname_UA\n" +
            "FROM\n" +
                "specialization s\n" +
            "JOIN\n" +
                "account_details a ON s.updated_by=a.id";
    // medical_card
    public static final String FIND_MEDICAL_CARD_BY_ID = "SELECT * FROM medical_card WHERE id=?";
    public static final String INSERT_MEDICAL_CARD =
            "INSERT INTO medical_card(updated_by, patient_id, doctor_id) VALUES(?, ?, ?)";
    public static final String UPDATE_MEDICAL_CARD = "UPDATE medical_card SET is_discharged=?, updated_by=?, " +
            "patient_id=?, doctor_id=? WHERE id=?";
    // medical_card_doctor bean
    public static final String FIND_ALL_MEDICAL_CARD_DOCTOR_BEANS_BY_PATIENT_ID =
            "SELECT\n" +
                "mc.*,\n" +
                "ub.name_EN updatedBy_name_EN,\n" +
                "ub.surname_EN updatedBy_surname_EN,\n" +
                "ub.name_UA updatedBy_name_UA,\n" +
                "ub.surname_UA updatedBy_surname_UA,\n" +
                "dad.name_EN doctor_name_EN,\n" +
                "dad.surname_EN doctor_surname_EN,\n" +
                "dad.name_UA doctor_name_UA,\n" +
                "dad.surname_UA doctor_surname_UA,\n" +
                "ds.name_EN specialization_name_EN,\n" +
                "ds.name_UA specialization_name_UA\n" +
            "FROM\n" +
                "medical_card mc\n" +
            "JOIN\n" +
                "account_details ub ON mc.updated_by=ub.id\n" +
            "LEFT JOIN\n" +
                "account_details dad ON mc.doctor_id=dad.id\n" +
            "LEFT JOIN\n" +
                "doctor d ON mc.doctor_id=d.id\n" +
            "LEFT JOIN\n" +
                "specialization ds ON d.specialization_id=ds.id\n" +
            "WHERE\n" +
                "mc.patient_id=?";
}
