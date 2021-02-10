package ua.com.util;

import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

public class Validator {
    private static final int MIN_LOGIN_LENGTH = 5;
    private static final int MAX_LOGIN_LENGTH = 16;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 16;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 45;
    private static final int MIN_SURNAME_LENGTH = 2;
    private static final int MAX_SURNAME_LENGTH = 45;
    private static final int MIN_EMAIL_LENGTH = 6;
    private static final int MAX_EMAIL_LENGTH = 45;

    private static final Pattern LOGIN_PATTERN = Pattern.compile("^(?![-_\\d])((?![-_]{2})[\\w-])+(?<![-_])$");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?:(?=.*?\\p{N})(?=.*?\\p{Lu})(?=.*?\\p{Ll}))[^\\p{C}]+$");
    private static final Pattern ACC_DETAILS_NAME_AND_SURNAME_EN_PATTERN = Pattern.compile("^[\\w\\d-_ ]+$");
    private static final Pattern ACC_DETAILS_NAME_AND_SURNAME_UA_PATTERN = Pattern.compile("^[а-їѓА-ЯІЇЄЁ\\d-_ ]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z][.[^@\\s]]+@[.[^@\\s]]+\\.[a-zA-Z]+$");

    private Validator() {
        // util class
    }

    /**
     * Checks if the givev string null or empty.
     *
     * @param s to be checked
     * @return true if mathes at least one condition
     */
    private static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    /**
     * Validates if the login null, empty, less than {@value MIN_LOGIN_LENGTH} characters,
     * more than {@value MAX_LOGIN_LENGTH} characters,
     * starts with '-_' or any number, contains two '-_' in a row, ends with '-_'.
     *
     * @param login to validate
     * @return true if the login matches at least one condition
     */
    public static boolean isLoginNotValid(String login) {
        return isNullOrEmpty(login) || login.length() < MIN_LOGIN_LENGTH ||
                login.length() > MAX_LOGIN_LENGTH || !LOGIN_PATTERN.matcher(login).matches();
    }

    /**
     * Validates if the password null, empty, less than {@value MIN_PASSWORD_LENGTH} characters,
     * more than {@value MAX_PASSWORD_LENGTH} characters,
     * does not contain: at least one number, one uppercase character, one lowercase character,
     * contains any control characters.
     *
     * @param password to validate
     * @return true if the password matches at least one condition
     */
    public static boolean isPasswordNotValid(String password) {
        return isNullOrEmpty(password) || password.length() < MIN_PASSWORD_LENGTH ||
                password.length() > MAX_PASSWORD_LENGTH || !PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Validates if the nameEN consists from latin letters.
     *
     * @param nameEN to validate
     * @return true if the nameEN matches condition
     */
    public static boolean isAccDetailsNameENNotValid(String nameEN) {
        return isNameNotValid(nameEN) || !ACC_DETAILS_NAME_AND_SURNAME_EN_PATTERN.matcher(nameEN).matches();
    }

    /**
     * Validates if the nameEN consists from cyrillic letters.
     *
     * @param nameUA to validate
     * @return true if the nameUA matches condition
     */
    public static boolean isAccDetailsNameUANotValid(String nameUA) {
        return isNameNotValid(nameUA) || !ACC_DETAILS_NAME_AND_SURNAME_UA_PATTERN.matcher(nameUA).matches();
    }

    /**
     * Validates if the name null, empty, less than {@value MIN_NAME_LENGTH} characters,
     * more than {@value MAX_NAME_LENGTH} characters.
     *
     * @param name to validate
     * @return true if the name matches at least one condition
     */
    private static boolean isNameNotValid(String name) {
        return isNullOrEmpty(name) || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH;
    }

    /**
     * Validates if the surnameEN consists from latin letters.
     *
     * @param surnameEN to validate
     * @return true if the surnameEN matches condition
     */
    public static boolean isSurnameENNotValid(String surnameEN) {
        return isSurnameNotValid(surnameEN) || !ACC_DETAILS_NAME_AND_SURNAME_EN_PATTERN.matcher(surnameEN).matches();
    }

    /**
     * Validates if the surnameUA consists from cyrillic letters.
     *
     * @param surnameUA to validate
     * @return true if the surnameUA matches condition
     */
    public static boolean isSurnameUANotValid(String surnameUA) {
        return isSurnameNotValid(surnameUA) || !ACC_DETAILS_NAME_AND_SURNAME_UA_PATTERN.matcher(surnameUA).matches();
    }

    /**
     * Validates if the surname null, empty, less than {@value MIN_SURNAME_LENGTH} characters,
     * more than {@value MAX_SURNAME_LENGTH} characters.
     *
     * @param surname to validate
     * @return true if the surname matches at least one condition
     */
    private static boolean isSurnameNotValid(String surname) {
        return isNullOrEmpty(surname) || surname.length() < MIN_SURNAME_LENGTH || surname.length() > MAX_SURNAME_LENGTH;
    }

    /**
     * Validates if the given email null, empty, less than {@value MIN_EMAIL_LENGTH} characters,
     * more than {@value MAX_LOGIN_LENGTH} characters, does not start from latin letter,
     * has more than one '@' character, contains any whitespace characters.
     *
     * @param email to validate
     * @return true if the email matches at least one condition
     */
    public static boolean isEmailNotValid(String email) {
        return isNullOrEmpty(email) || email.length() < MIN_EMAIL_LENGTH || email.length() > MAX_EMAIL_LENGTH ||
                !EMAIL_PATTERN.matcher(email).matches();
    }

    public static void setErrorMessage(HttpSession session, String errorMessage, Logger logger, String forward) {
        setErrorMessage(session, errorMessage, logger);
        logger.debug("forward to -> {}", forward);
    }

    public static void setErrorMessage(HttpSession session, String errorMessage, Logger logger){
        session.setAttribute("errorMessage", errorMessage);
        logger.error("error message -> {}", errorMessage);
    }
}
