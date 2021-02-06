package ua.com.entity;

/**
 * This enum represents the role table in the database.
 *
 * @author Orest Dmyterko
 */
public enum Role {
    ADMIN, DOCTOR, NURSE, PATIENT;

    public static Role getRole(int roleId) {
        return Role.values()[roleId];
    }
}