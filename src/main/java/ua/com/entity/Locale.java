package ua.com.entity;

/**
 * This enum represents the locale table in the database.
 *
 * @author Orest Dmyterko
 */
public enum Locale {
    EN(1), UA(2);

    private int id;

    Locale(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
