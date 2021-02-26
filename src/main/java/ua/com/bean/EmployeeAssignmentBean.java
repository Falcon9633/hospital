package ua.com.bean;

import ua.com.entity.Assignment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class EmployeeAssignmentBean {
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

    private LocalDate patientBirthday;
    private String patientNameEN;
    private String patientSurnameEN;
    private String patientNameUA;
    private String patientSurnameUA;

    private String createdByNameEN;
    private String createdBySurnameEN;
    private String createdByNameUA;
    private String createdBySurnameUA;

    public EmployeeAssignmentBean(Assignment assignment) {
        this.id = assignment.getId();
        this.nameEN = assignment.getNameEN();
        this.nameUA = assignment.getNameUA();
        this.descriptionEN = assignment.getDescriptionEN();
        this.descriptionUA = assignment.getDescriptionUA();
        this.end = assignment.isEnd();
        this.createTime = assignment.getCreateTime();
        this.createdBy = assignment.getCreatedBy();
        this.servedBy = assignment.getServedBy();
        this.medicalCardId = assignment.getMedicalCardId();
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

    public LocalDate getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(LocalDate patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public String getPatientNameEN() {
        return patientNameEN;
    }

    public void setPatientNameEN(String patientNameEN) {
        this.patientNameEN = patientNameEN;
    }

    public String getPatientSurnameEN() {
        return patientSurnameEN;
    }

    public void setPatientSurnameEN(String patientSurnameEN) {
        this.patientSurnameEN = patientSurnameEN;
    }

    public String getPatientNameUA() {
        return patientNameUA;
    }

    public void setPatientNameUA(String patientNameUA) {
        this.patientNameUA = patientNameUA;
    }

    public String getPatientSurnameUA() {
        return patientSurnameUA;
    }

    public void setPatientSurnameUA(String patientSurnameUA) {
        this.patientSurnameUA = patientSurnameUA;
    }

    public String getCreatedByNameEN() {
        return createdByNameEN;
    }

    public void setCreatedByNameEN(String createdByNameEN) {
        this.createdByNameEN = createdByNameEN;
    }

    public String getCreatedBySurnameEN() {
        return createdBySurnameEN;
    }

    public void setCreatedBySurnameEN(String createdBySurnameEN) {
        this.createdBySurnameEN = createdBySurnameEN;
    }

    public String getCreatedByNameUA() {
        return createdByNameUA;
    }

    public void setCreatedByNameUA(String createdByNameUA) {
        this.createdByNameUA = createdByNameUA;
    }

    public String getCreatedBySurnameUA() {
        return createdBySurnameUA;
    }

    public void setCreatedBySurnameUA(String createdBySurnameUA) {
        this.createdBySurnameUA = createdBySurnameUA;
    }

    @Override
    public String toString() {
        return "EmployeeAssignmentBean{" +
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
                ", patientBirthday=" + patientBirthday +
                ", patientNameEN='" + patientNameEN + '\'' +
                ", patientSurnameEN='" + patientSurnameEN + '\'' +
                ", patientNameUA='" + patientNameUA + '\'' +
                ", patientSurnameUA='" + patientSurnameUA + '\'' +
                ", createdByNameEN='" + createdByNameEN + '\'' +
                ", createdBySurnameEN='" + createdBySurnameEN + '\'' +
                ", createdByNameUA='" + createdByNameUA + '\'' +
                ", createdBySurnameUA='" + createdBySurnameUA + '\'' +
                '}';
    }
}
