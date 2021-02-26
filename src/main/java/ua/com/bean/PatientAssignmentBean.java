package ua.com.bean;

import ua.com.entity.Assignment;
import ua.com.entity.Specialization;

import java.time.LocalDateTime;

public class PatientAssignmentBean {
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

    private String empNameEN;
    private String empSurnameEN;
    private String empNameUA;
    private String empSurnameUA;
    private String doctorSpecializationNameEN;
    private String doctorSpecializationNameUA;

    private String createdByNameEN;
    private String createdBySurnameEN;
    private String createdByNameUA;
    private String createdBySurnameUA;

    public PatientAssignmentBean(Assignment assignment) {
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

    public String getEmpNameEN() {
        return empNameEN;
    }

    public void setEmpNameEN(String empNameEN) {
        this.empNameEN = empNameEN;
    }

    public String getEmpSurnameEN() {
        return empSurnameEN;
    }

    public void setEmpSurnameEN(String empSurnameEN) {
        this.empSurnameEN = empSurnameEN;
    }

    public String getEmpNameUA() {
        return empNameUA;
    }

    public void setEmpNameUA(String empNameUA) {
        this.empNameUA = empNameUA;
    }

    public String getEmpSurnameUA() {
        return empSurnameUA;
    }

    public void setEmpSurnameUA(String empSurnameUA) {
        this.empSurnameUA = empSurnameUA;
    }

    public String getDoctorSpecializationNameEN() {
        return doctorSpecializationNameEN;
    }

    public void setDoctorSpecializationNameEN(String doctorSpecializationNameEN) {
        this.doctorSpecializationNameEN = doctorSpecializationNameEN;
    }

    public String getDoctorSpecializationNameUA() {
        return doctorSpecializationNameUA;
    }

    public void setDoctorSpecializationNameUA(String doctorSpecializationNameUA) {
        this.doctorSpecializationNameUA = doctorSpecializationNameUA;
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
        return "PatientAssignmentBean{" +
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
                ", doctorNameEN='" + empNameEN + '\'' +
                ", doctorSurnameEN='" + empSurnameEN + '\'' +
                ", doctorNameUA='" + empNameUA + '\'' +
                ", doctorSurnameUA='" + empSurnameUA + '\'' +
                ", doctorSpecializationNameEN='" + doctorSpecializationNameEN + '\'' +
                ", doctorSpecializationNameUA='" + doctorSpecializationNameUA + '\'' +
                ", createdByNameEN='" + createdByNameEN + '\'' +
                ", createdBySurnameEN='" + createdBySurnameEN + '\'' +
                ", createdByNameUA='" + createdByNameUA + '\'' +
                ", createdBySurnameUA='" + createdBySurnameUA + '\'' +
                '}';
    }

    public void setSpecialization(Specialization specialization) {
        this.doctorSpecializationNameEN = specialization.getNameEN();
        this.doctorSpecializationNameUA = specialization.getNameUA();
    }
}
