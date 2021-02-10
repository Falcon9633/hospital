package ua.com.entity;

/**
 * This enum represents the locale table in the database.
 *
 * @author Orest Dmyterko
 */
public enum Locale {
    EN, UA;

    public static Locale getLocale(int localeId){
        return Locale.values()[localeId];
    }
}
