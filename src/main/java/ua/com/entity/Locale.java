package ua.com.entity;

import java.util.ResourceBundle;

/**
 * This enum represents the locale table in the database.
 *
 * @author Orest Dmyterko
 */
public enum Locale {
    EN("en", "US"), UA("uk", "UA");

    private final java.util.Locale javaLocale;

    Locale(String language, String country) {
        this.javaLocale = new java.util.Locale(language, country);
    }

    public static Locale getLocale(int localeId){
        return Locale.values()[localeId];
    }

    public String getMessage(String key){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources", this.javaLocale);
        return resourceBundle.getString(key);
    }

    public String getName(){
        return javaLocale.toString();
    }
}
