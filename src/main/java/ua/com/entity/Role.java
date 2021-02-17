package ua.com.entity;

/**
 * This enum represents the role table in the database.
 *
 * @author Orest Dmyterko
 */
public enum Role {
    ADMIN("Administrator", "Адміністратор"), DOCTOR("Doctor", "Лікар"),
    NURSE("Nurse", "Медсестра"), PATIENT("Patient", "Пацієнт");

    private final String nameEN;
    private final String nameUA;

    Role(String nameEN, String nameUA) {
        this.nameEN = nameEN;
        this.nameUA = nameUA;
    }

    public static Role getRole(int roleId) {
        return Role.values()[roleId];
    }

    public String getNameEN() {
        return nameEN;
    }

    public String getNameUA() {
        return nameUA;
    }
}