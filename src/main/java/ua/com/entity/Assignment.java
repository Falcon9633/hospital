package ua.com.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Assignment {
    private Long id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private boolean end;
    private LocalDateTime createTime;
    private Long createdBy;
    private Long servedBy;
    private Long medicalCardId;

    public Assignment() {
    }

    public Assignment(String nameEN, String nameUA, String descriptionEN, String descriptionUA, Long createdBy, Long servedBy, Long medicalCardId) {
        this.nameEN = nameEN;
        this.nameUA = nameUA;
        this.descriptionEN = descriptionEN;
        this.descriptionUA = descriptionUA;
        this.createdBy = createdBy;
        this.servedBy = servedBy;
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

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
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

    public Long getServedBy() {
        return servedBy;
    }

    public void setServedBy(Long servedBy) {
        this.servedBy = servedBy;
    }

    public Long getMedicalCardId() {
        return medicalCardId;
    }

    public void setMedicalCardId(Long medicalCardId) {
        this.medicalCardId = medicalCardId;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", descriptionEN='" + descriptionEN + '\'' +
                ", descriptionUA='" + descriptionUA + '\'' +
                ", end=" + end +
                ", createTime=" + createTime +
                ", createdBy=" + createdBy +
                ", servedBy=" + servedBy +
                ", medicalCardId=" + medicalCardId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return end == that.end && Objects.equals(id, that.id) && Objects.equals(nameEN, that.nameEN) && Objects.equals(nameUA, that.nameUA) && Objects.equals(descriptionEN, that.descriptionEN) && Objects.equals(descriptionUA, that.descriptionUA) && Objects.equals(createTime, that.createTime) && Objects.equals(createdBy, that.createdBy) && Objects.equals(servedBy, that.servedBy) && Objects.equals(medicalCardId, that.medicalCardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEN, nameUA, descriptionEN, descriptionUA, end, createTime, createdBy, servedBy, medicalCardId);
    }
}
