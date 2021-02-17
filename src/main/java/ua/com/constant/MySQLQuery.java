package ua.com.constant;

/**
 * Holds MySql queries.
 *
 * @author OrestDmyterko
 */
public class MySQLQuery {
    // account
    public static final String FIND_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id=?";
    public static final String FIND_ACCOUNT_BY_LOGIN = "SELECT * FROM account WHERE login=?";
    public static final String INSERT_ACCOUNT =
            "INSERT INTO account(login, password, email, updated_by, role_id) VALUES(?, ?, ?, ?, ?)";
    public static final String UPDATE_ACCOUNT = "UPDATE account SET login=?, password=?, email=?, locked=?, " +
            "updated_by=?, role_id=?, locale_id=? WHERE id=?";
    // account details
    public static final String FIND_ACCOUNT_DETAILS_BY_ID = "SELECT * FROM account_details where id=?";
    public static final String INSERT_ACCOUNT_DETAILS =
            "INSERT INTO account_details(id, name_EN, surname_EN, name_UA, surname_UA) VALUES(?, ?, ?, ?, ?)";
    public static final String UPDATE_ACCOUNT_DETAILS = "UPDATE account_details SET name_EN=?, surname_EN=?, " +
            "name_UA=?, surname_UA=? WHERE id=?";
    // doctor
    public static final String INSERT_DOCTOR = "INSERT INTO doctor(id, specialization_id) VALUES(?, ?)";
    // patient
    public static final String FIND_PATIENT_BY_ID = "SELECT * FROM patient WHERE id=?";
    public static final String INSERT_PATIENT = "INSERT INTO patient(id, birthday) VALUES(?, ?)";
    public static final String GET_NUMBER_OF_ROWS_PATIENT = "SELECT COUNT(id) number FROM patient";
    public static final String UPDATE_PATIENT = "UPDATE patient SET birthday=? WHERE id=?";
    // patient_account bean
    public static final String FIND_ALL_PATIENT_ACCOUNT_BEANS =
            "SELECT p.*, ad.name_EN, ad.surname_EN, ad.name_UA, ad.surname_UA, a.email, a.locked, a.create_time,\n" +
                    "a.update_time, updateBy.name_EN updateBy_name_EN, updateBy.surname_EN updateBy_surname_EN,\n" +
                    "updateBy.name_UA updateBy_name_UA, updateBy.surname_UA updateBy_surname_UA\n" +
                    "from patient p\n" +
                    "JOIN account a ON p.id=a.id\n" +
                    "JOIN account_details ad ON p.id=ad.id\n" +
                    "JOIN account_details updateBy ON a.updated_by=updateBy.id ORDER BY %s %s\n" +
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
            "SELECT s.*, a.name_EN updateBy_name_EN, a.surname_EN updateBy_surname_EN,\n" +
                    "a.name_UA updateBy_name_UA, a.surname_UA updateBy_surname_UA\n" +
                    "FROM specialization s\n" +
                    "JOIN account_details a ON s.updated_by=a.id";
}
