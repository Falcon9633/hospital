package ua.com.util;

import org.apache.logging.log4j.Logger;
import ua.com.constant.SorterConstants;
import ua.com.entity.Locale;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

public class Validator {
    private static final int MIN_ACCOUNT_LOGIN_LENGTH = 5;
    private static final int MAX_ACCOUNT_LOGIN_LENGTH = 16;
    private static final int MIN_ACCOUNT_PASSWORD_LENGTH = 6;
    private static final int MAX_ACCOUNT_PASSWORD_LENGTH = 16;
    private static final int MIN_ACC_DETAILS_NAME_SURNAME_LENGTH = 2;
    private static final int MAX_ACC_DETAILS_NAME_SURNAME_LENGTH = 45;
    private static final int MIN_ACC_DETAILS_EMAIL_LENGTH = 6;
    private static final int MAX_ACC_DETAILS_EMAIL_LENGTH = 45;
    private static final int MIN_SPECIALIZATION_NAME_LENGTH = 3;
    private static final int MAX_SPECIALIZATION_NAME_LENGTH = 45;
    private static final int MIN_ASSIGMENT_NAME_LENGTH = 3;
    private static final int MAX_ASSIGMENT_NAME_LENGTH = 45;
    private static final int MAX_DESCRIPTION_LENGTH = 1024;

    private static final Pattern LOGIN_PATTERN = Pattern.compile("^(?![-_\\d])((?![-_]{2})[\\w-])+(?<![-_])$");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?:(?=.*?\\p{N})(?=.*?\\p{Lu})(?=.*?\\p{Ll}))[^\\p{C}]+$");
    private static final Pattern ACC_DETAILS_NAME_SURNAME_EN_PATTERN = Pattern.compile("^[\\w\\d-_ ]+$");
    private static final Pattern ACC_DETAILS_NAME_SURNAME_UA_PATTERN = Pattern.compile("^[а-їѓА-ЯІЇЄЁ\\d-_ ]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z][.[^@\\s]]+@[.[^@\\s]]+\\.[a-zA-Z]+$");

    public static final String CHECKBOX_ON = "on";

    private Validator() {
        // util class
    }

    /**
     * Checks if the given string null or empty.
     *
     * @param s to be checked
     * @return true if matches at least one condition
     */
    private static boolean isNullOrEmpty(String s, HttpSession session, Locale locale, Logger logger, String forward) {
        if (s == null || s.isEmpty()) {
            setErrorMessage(session, locale.getMessage("validation.error.is_null_or_empty"), logger, forward);
            return true;
        }
        return false;
    }

    /**
     * Checks if a given string length is more then a given length.
     *
     * @param s      to be checked
     * @param length to compare with
     * @return true if matches a condition
     */
    private static boolean isMoreThanLength(String s, int length, HttpSession session, Locale locale, Logger logger, String forward) {
        if (s != null && s.length() > length) {
            String errMsg = locale.getMessage("validation.error.is_more_than_length");
            String ch = locale.getMessage("validation.error.characters");
            setErrorMessage(session,
                    String.format("'%s' %s %d %s", s, errMsg, length, ch),
                    logger, forward);
            return true;
        }
        return false;
    }

    /**
     * Checks if a given string length is less then a given length.
     *
     * @param s      to be checked
     * @param length to compare with
     * @return true if matches a condition
     */
    private static boolean isLessThanLength(String s, int length, HttpSession session, Locale locale, Logger logger, String forward) {
        if (s.length() < length) {
            String errMsg = locale.getMessage("validation.error.is_less_than_length");
            String ch = locale.getMessage("validation.error.characters");
            setErrorMessage(session,
                    String.format("'%s' %s %d %s", s, errMsg, length, ch),
                    logger, forward);
            return true;
        }
        return false;
    }

    /**
     * Checks if a login starts with '-_' or any number, contains two '-_' in a row, ends with '-_'.
     *
     * @param login to be checked
     * @return true if matches at least one condition
     */
    private static boolean isLoginNotMatchPattern(String login, HttpSession session, Locale locale, Logger logger, String forward) {
        if (!LOGIN_PATTERN.matcher(login).matches()) {
            setErrorMessage(session,
                    login + " " + locale.getMessage("validation.error.account_login_pattern"),
                    logger, forward);
            return true;
        }
        return false;
    }

    /**
     * Validates a login.
     *
     * @param login to validate
     * @return true if the login matches at least one condition
     */
    public static boolean isLoginNotValid(String login, HttpSession session, Locale locale, Logger logger, String forward) {
        return isNullOrEmpty(login, session, locale, logger, forward) ||
                isMoreThanLength(login, MAX_ACCOUNT_LOGIN_LENGTH, session, locale, logger, forward) ||
                isLessThanLength(login, MIN_ACCOUNT_LOGIN_LENGTH, session, locale, logger, forward) ||
                isLoginNotMatchPattern(login, session, locale, logger, forward);
    }

    /**
     * Checks if a password contains any control character, does not contain: at least one number, uppercase letter,
     * lowercase letter.
     *
     * @param password to be checked
     * @return true if matches at least one condition
     */
    private static boolean isPasswordNotMatchPattern(String password, HttpSession session, Locale locale, Logger logger, String forward) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            setErrorMessage(session,
                    locale.getMessage("validation.error.account_password_pattern"), logger, forward);
            return true;
        }
        return false;
    }

    /**
     * Validates a password.
     *
     * @param password to validate
     * @return true if the password matches at least one condition
     */
    public static boolean isPasswordNotValid(String password, HttpSession session, Locale locale, Logger logger, String forward) {
        return isNullOrEmpty(password, session, locale, logger, forward) ||
                isLessThanLength(password, MIN_ACCOUNT_PASSWORD_LENGTH, session, locale, logger, forward) ||
                isMoreThanLength(password, MAX_ACCOUNT_PASSWORD_LENGTH, session, locale, logger, forward) ||
                isPasswordNotMatchPattern(password, session, locale, logger, forward);
    }

    /**
     * Checks if a string consists of latin letters.
     *
     * @param s to be checked
     * @return true if matches a condition
     */
    private static boolean isAccDetailsNameSurnameENNotMatchPattern(String s, HttpSession session, Locale locale,
                                                                    Logger logger, String forward) {
        if (!ACC_DETAILS_NAME_SURNAME_EN_PATTERN.matcher(s).matches()) {
            setErrorMessage(session, locale.getMessage("validator.error.acc_details_name_surname_en_pattern"),
                    logger, forward);
            return true;
        }
        return false;
    }

    /**
     * Checks if a string consists of cyrillic letters.
     *
     * @param s to be checked
     * @return true if matches a condition
     */
    private static boolean isAccDetailsNameSurnameUANotMatchPattern(String s, HttpSession session, Locale locale,
                                                                    Logger logger, String forward) {
        if (!ACC_DETAILS_NAME_SURNAME_UA_PATTERN.matcher(s).matches()) {
            setErrorMessage(session, locale.getMessage("validator.error.acc_details_name_surname_ua_pattern"),
                    logger, forward);
            return true;
        }
        return false;
    }

    /**
     * Validates a nameEN or a surnameEN.
     *
     * @param s to validate
     * @return true if the s matches condition
     */
    public static boolean isAccDetailsNameSurnameENNotValid(String s, HttpSession session, Locale locale, Logger logger, String forward) {
        return isNameSurnameNotValid(s, session, locale, logger, forward) ||
                isAccDetailsNameSurnameENNotMatchPattern(s, session, locale, logger, forward);
    }

    /**
     * Validates a nameUA or a surnameUA.
     *
     * @param s to validate
     * @return true if the s matches condition
     */
    public static boolean isAccDetailsNameSurnameUANotValid(String s, HttpSession session, Locale locale, Logger logger, String forward) {
        return isNameSurnameNotValid(s, session, locale, logger, forward) ||
                isAccDetailsNameSurnameUANotMatchPattern(s, session, locale, logger, forward);

    }

    /**
     * Validates if a name or surname null, empty, less than {@value MIN_ACC_DETAILS_NAME_SURNAME_LENGTH} characters,
     * more than {@value MAX_ACC_DETAILS_NAME_SURNAME_LENGTH} characters.
     *
     * @param s to validate
     * @return true if the s matches at least one condition
     */
    private static boolean isNameSurnameNotValid(String s, HttpSession session, Locale locale, Logger logger, String forward) {
        return isNullOrEmpty(s, session, locale, logger, forward) ||
                isLessThanLength(s, MIN_ACC_DETAILS_NAME_SURNAME_LENGTH, session, locale, logger, forward) ||
                isMoreThanLength(s, MAX_ACC_DETAILS_NAME_SURNAME_LENGTH, session, locale, logger, forward);
    }

    private static boolean isAccDetailsEmailNotMatchPattern(String email, HttpSession session, Locale locale, Logger logger, String forward) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            setErrorMessage(session, locale.getMessage("validator.error.acc_details_email_pattern"),
                    logger, forward);
            return true;
        }
        return false;
    }

    /**
     * Validates if the given email null, empty, less than {@value MIN_ACC_DETAILS_EMAIL_LENGTH} characters,
     * more than {@value MAX_ACCOUNT_LOGIN_LENGTH} characters, does not start from latin letter,
     * has more than one '@' character, contains any whitespace characters.
     *
     * @param email to validate
     * @return true if the email matches at least one condition
     */
    public static boolean isEmailNotValid(String email, HttpSession session, Locale locale, Logger logger, String forward) {
        return isNullOrEmpty(email, session, locale, logger, forward) ||
                isLessThanLength(email, MIN_ACC_DETAILS_EMAIL_LENGTH, session, locale, logger, forward) ||
                isMoreThanLength(email, MAX_ACC_DETAILS_EMAIL_LENGTH, session, locale, logger, forward) ||
                isAccDetailsEmailNotMatchPattern(email, session, locale, logger, forward);
    }

    public static boolean isSpecializationNameNotValid(String name, HttpSession session, Locale locale, Logger logger, String forward) {
        return isNullOrEmpty(name, session, locale, logger, forward) ||
                isLessThanLength(name, MIN_SPECIALIZATION_NAME_LENGTH, session, locale, logger, forward) ||
                isMoreThanLength(name, MAX_SPECIALIZATION_NAME_LENGTH, session, locale, logger, forward);
    }

    public static boolean isAssigmentNameNotValid(String name, HttpSession session, Locale locale, Logger logger, String forward) {
        return isNullOrEmpty(name, session, locale, logger, forward) ||
                isLessThanLength(name, MIN_ASSIGMENT_NAME_LENGTH, session, locale, logger, forward) ||
                isMoreThanLength(name, MAX_ASSIGMENT_NAME_LENGTH, session, locale, logger, forward);
    }

    public static boolean isDescriptionNotValid(String s, HttpSession session, Locale locale, Logger logger, String forward) {
        return isMoreThanLength(s, MAX_DESCRIPTION_LENGTH, session, locale, logger, forward);
    }

    public static boolean isRequestedIdValid(Long id) {
        return id != null && id != 0;
    }

    public static boolean isRequestedIdValid(Integer id) {
        return id != null && id != 0;
    }

    public static String checkDoctorSpecSortBy(String sortBy) {
        if (!SorterConstants.NAME.equals(sortBy)) {
            return SorterConstants.NAME;
        }
        return sortBy;
    }

    public static String checkUsersPatientSortBy(String sortBy) {
        if (!SorterConstants.SURNAME.equals(sortBy) && !SorterConstants.BIRTHDAY.equals(sortBy)) {
            return SorterConstants.SURNAME;
        }
        return sortBy;
    }

    public static String checkUsersDoctorSortBy(String sortBy) {
        if (!SorterConstants.SURNAME.equals(sortBy) &&
                !SorterConstants.SPECIALIZATION.equals(sortBy) &&
                !SorterConstants.PATIENT_COUNT.equals(sortBy)) {
            return SorterConstants.SURNAME;
        }
        return sortBy;
    }

    public static String checkSortingDirection(String direction) {
        if (!SorterConstants.ASC.equals(direction) && !SorterConstants.DESC.equals(direction)) {
            return SorterConstants.ASC;
        }
        return direction;
    }

    public static boolean checkInputCheckbox(String input) {
        return CHECKBOX_ON.equals(input);
    }

    public static int checkLocaleId(int localeId) {
        if (localeId < 0 || localeId > 1) {
            return 0;
        }
        return localeId;
    }

    public static void setErrorMessage(HttpSession session, String errorMessage, Logger logger, String forward) {
        setErrorMessage(session, errorMessage, logger);
        logger.debug("forward to -> {}", forward);
    }

    public static void setErrorMessage(HttpSession session, String errorMessage, Logger logger) {
        session.setAttribute("errorMessage", errorMessage);
        logger.error("error message -> {}", errorMessage);
    }
}
