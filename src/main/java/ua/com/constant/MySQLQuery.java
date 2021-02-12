package ua.com.constant;

/**
 * Holds MySql queries.
 *
 * @author OrestDmyterko
 */
public class MySQLQuery {
    // account
    public static final String FIND_ACCOUNT_BY_LOGIN = "SELECT * FROM account WHERE login=?";
    public static final String INSERT_ACCOUNT =
            "INSERT INTO account(login, password, updated_by, role_id) VALUES(?, ?, ?, ?)";
    // account details
    public static final String FIND_ACCOUNT_DETAILS_BY_ID = "SELECT * FROM account_details where id=?";
    public static final String INSERT_ACCOUNT_DETAILS =
            "INSERT INTO account_details(id, name_EN, surname_EN, name_UA, surname_UA, email) VALUES(?, ?, ?, ?, ?, ?)";
    // doctor
    public static final String INSERT_DOCTOR = "INSERT INTO doctor(id, specialization_id) VALUES(?, ?)";
    // patient
    public static final String INSERT_PATIENT = "INSERT INTO patient(id, birthday) VALUES(?, ?)";
    // specialization
    public static final String FIND_ALL_SPECIALIZATIONS = "SELECT * FROM specialization";
}
