package ua.com.entity;

import java.time.LocalDate;

/**
 * This class represents the patient table in the database.
 *
 * @author Orest Dmyterko
 */
public class Patient {
    private Long id;
    private LocalDate birthday;

    public Patient() {
    }

    public Patient(Long id, LocalDate birthday) {
        this.id = id;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", birthday=" + birthday +
                '}';
    }
}
