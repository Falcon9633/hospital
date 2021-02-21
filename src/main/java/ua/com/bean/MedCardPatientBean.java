package ua.com.bean;

import ua.com.entity.MedicalCard;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedCardPatientBean {
    private Long id;
    private boolean isDischarged;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long updatedBy;
    private Long patientId;
    private Long doctorId;

    private String patientNameEN;
    private String patientSurnameEN;
    private String patientNameUA;
    private String patientSurnameUA;
    private LocalDate patientBirthday;

    public MedCardPatientBean() {
    }

    public MedCardPatientBean(MedicalCard medicalCard) {
        this.id = medicalCard.getId();
        this.isDischarged = medicalCard.isDischarged();
        this.createTime = medicalCard.getCreateTime();
        this.updateTime = medicalCard.getUpdateTime();
        this.updatedBy = medicalCard.getUpdatedBy();
        this.patientId = medicalCard.getPatientId();
        this.doctorId = medicalCard.getDoctorId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDischarged() {
        return isDischarged;
    }

    public void setDischarged(boolean discharged) {
        isDischarged = discharged;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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

    public LocalDate getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(LocalDate patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    @Override
    public String toString() {
        return "MedCardPatientBean{" +
                "id=" + id +
                ", isDischarged=" + isDischarged +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updatedBy=" + updatedBy +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", patientNameEn='" + patientNameEN + '\'' +
                ", patientSurnameEN='" + patientSurnameEN + '\'' +
                ", patientNameUA='" + patientNameUA + '\'' +
                ", patientSurnameUA='" + patientSurnameUA + '\'' +
                ", patientBirthday=" + patientBirthday +
                '}';
    }
}
