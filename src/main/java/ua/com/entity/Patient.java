package ua.com.entity;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(birthday, patient.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthday);
    }
}
