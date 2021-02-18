package ua.com.bean;

import java.time.LocalDateTime;

public class MedCardDoctorBean {
    private Long id;
    private boolean isDischarged;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long updatedBy;
    private Long patientId;
    private Long doctorId;

    private String updatedByNameEN;
    private String updatedBySurnameEN;
    private String updatedByNameUA;
    private String updatedBySurnameUA;

    private String doctorNameEN;
    private String doctorSurnameEN;
    private String doctorNameUA;
    private String doctorSurnameUA;

    private String specializationNameEN;
    private String specializationNameUA;

    public MedCardDoctorBean() {
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

    public String getUpdatedByNameEN() {
        return updatedByNameEN;
    }

    public void setUpdatedByNameEN(String updatedByNameEN) {
        this.updatedByNameEN = updatedByNameEN;
    }

    public String getUpdatedBySurnameEN() {
        return updatedBySurnameEN;
    }

    public void setUpdatedBySurnameEN(String updatedBySurnameEN) {
        this.updatedBySurnameEN = updatedBySurnameEN;
    }

    public String getUpdatedByNameUA() {
        return updatedByNameUA;
    }

    public void setUpdatedByNameUA(String updatedByNameUA) {
        this.updatedByNameUA = updatedByNameUA;
    }

    public String getUpdatedBySurnameUA() {
        return updatedBySurnameUA;
    }

    public void setUpdatedBySurnameUA(String updatedBySurnameUA) {
        this.updatedBySurnameUA = updatedBySurnameUA;
    }

    public String getDoctorNameEN() {
        return doctorNameEN;
    }

    public void setDoctorNameEN(String doctorNameEN) {
        this.doctorNameEN = doctorNameEN;
    }

    public String getDoctorSurnameEN() {
        return doctorSurnameEN;
    }

    public void setDoctorSurnameEN(String doctorSurnameEN) {
        this.doctorSurnameEN = doctorSurnameEN;
    }

    public String getDoctorNameUA() {
        return doctorNameUA;
    }

    public void setDoctorNameUA(String doctorNameUA) {
        this.doctorNameUA = doctorNameUA;
    }

    public String getDoctorSurnameUA() {
        return doctorSurnameUA;
    }

    public void setDoctorSurnameUA(String doctorSurnameUA) {
        this.doctorSurnameUA = doctorSurnameUA;
    }

    public String getSpecializationNameEN() {
        return specializationNameEN;
    }

    public void setSpecializationNameEN(String specializationNameEN) {
        this.specializationNameEN = specializationNameEN;
    }

    public String getSpecializationNameUA() {
        return specializationNameUA;
    }

    public void setSpecializationNameUA(String specializationNameUA) {
        this.specializationNameUA = specializationNameUA;
    }

    @Override
    public String toString() {
        return "MedCardDoctorBean{" +
                "id=" + id +
                ", isDischarged=" + isDischarged +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updatedBy=" + updatedBy +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", updatedByNameEN='" + updatedByNameEN + '\'' +
                ", updatedBySurnameEN='" + updatedBySurnameEN + '\'' +
                ", updatedByNameUA='" + updatedByNameUA + '\'' +
                ", updatedBySurnameUA='" + updatedBySurnameUA + '\'' +
                ", doctorNameEN='" + doctorNameEN + '\'' +
                ", doctorSurnameEN='" + doctorSurnameEN + '\'' +
                ", doctorNameUA='" + doctorNameUA + '\'' +
                ", doctorSurnameUA='" + doctorSurnameUA + '\'' +
                ", specializationNameEN='" + specializationNameEN + '\'' +
                ", specializationNameUA='" + specializationNameUA + '\'' +
                '}';
    }
}
