package ua.com.entity;

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
}
