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
    public static final String UPDATED_BY = "updated_by";
    public static final String NAME_EN = "name_EN";
    public static final String NAME_UA = "name_UA";

    // account
    public static final String ACCOUNT_LOGIN = "login";
    public static final String ACCOUNT_PASSWORD = "password";
    public static final String ACCOUNT_LOCKED = "locked";
    public static final String ACCOUNT_ROLE_ID = "role_id";
    public static final String ACCOUNT_LOCALE_ID = "locale_id";

    // account_details
    public static final String ACCOUNT_DETAILS_SURNAME_EN = "surname_EN";
    public static final String ACCOUNT_DETAILS_SURNAME_UA = "surname_UA";
    public static final String ACCOUNT_DETAILS_EMAIL = "email";

}
