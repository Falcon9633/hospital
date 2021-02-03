package ua.com.entity;

/**
 * This enum represents the role table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public enum Role {
    ADMIN(1), DOCTOR(2), NURSE(3), PATIENT(4);

    private int id;

    Role(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}