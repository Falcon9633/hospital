package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the diagnosis table in the database.
 *
 * @author Orest Dmyterko
 */
public class Diagnosis {
    private Long id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private LocalDateTime createTime;
    private Long createdBy;
    private Long medicalCardId;

    public Diagnosis() {
    }

    public Diagnosis(String nameEN, String nameUA, String descriptionEN, String descriptionUA, Long createdBy, Long medicalCardId) {
        this.nameEN = nameEN;
        this.nameUA = nameUA;
        this.descriptionEN = descriptionEN;
        this.descriptionUA = descriptionUA;
        this.createdBy = createdBy;
        this.medicalCardId = medicalCardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getDescriptionUA() {
        return descriptionUA;
    }

    public void setDescriptionUA(String descriptionUA) {
        this.descriptionUA = descriptionUA;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getMedicalCardId() {
        return medicalCardId;
    }

    public void setMedicalCardId(Long medicalCardId) {
        this.medicalCardId = medicalCardId;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", descriptionEN='" + descriptionEN + '\'' +
                ", descriptionUA='" + descriptionUA + '\'' +
                ", createTime=" + createTime +
                ", createdBy=" + createdBy +
                ", medicalCardId=" + medicalCardId +
                '}';
    }
}
