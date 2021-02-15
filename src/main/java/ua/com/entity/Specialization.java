package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the specialization table in the database.
 *
 * @author Orest Dmyterko
 */
public class Specialization {
    private Integer id;
    private String nameEN;
    private String nameUA;
    private LocalDateTime updateTime;
    private Long updatedBy;

    public Specialization() {
    }

    public Specialization(String nameEN, String nameUA, Long updatedBy) {
        this.nameEN = nameEN;
        this.nameUA = nameUA;
        this.updatedBy = updatedBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", updateTime=" + updateTime +
                ", updatedBy=" + updatedBy +
                '}';
    }
}
