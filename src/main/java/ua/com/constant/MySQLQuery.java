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
    public static final String FIND_SPECIALIZATION_BY_ID = "SELECT * FROM specialization WHERE id=?";
    public static final String FIND_SPECIALIZATION_BY_NAME = "SELECT * FROM specialization WHERE name_en=? OR name_ua=?";
    public static final String INSERT_SPECIALIZATION =
            "INSERT INTO specialization(name_EN, name_UA, updated_by) VALUES(?, ?, ?)";
    public static final String UPDATE_SPECIALIZATION = "UPDATE specialization SET name_EN=?, name_UA=?, updated_by=? WHERE id=?";
    // specialization_account_details bean
    public static final String FIND_ALL_SPECIALIZATION_ACC_DETAILS_BEANS =
            "SELECT s.*, a.name_EN account_name_EN, a.surname_EN account_surname_EN, " +
                    "a.name_UA account_name_UA, a.surname_UA account_surname_UA\n" +
                    "FROM specialization s\n" +
                    "JOIN account_details a ON s.updated_by=a.id";
}
