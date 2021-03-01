package ua.com.entity;

import java.util.Objects;

/**
 * This class represents the doctor table in the database.
 *
 * @author Orest Dmyterko
 */
public class Doctor {
    private Long id;
    private Integer specializationId;

    public Doctor() {
    }

    public Doctor(Long id, Integer specializationId) {
        this.id = id;
        this.specializationId = specializationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", specializationId=" + specializationId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(specializationId, doctor.specializationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specializationId);
    }
}
