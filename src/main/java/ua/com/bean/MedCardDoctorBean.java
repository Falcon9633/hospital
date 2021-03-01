package ua.com.bean;

import ua.com.entity.AccountDetails;
import ua.com.entity.MedicalCard;
import ua.com.entity.Specialization;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public MedCardDoctorBean(MedicalCard medicalCard, AccountDetails doctorAccDetails, AccountDetails updatedByAccDetails, Specialization specialization) {
        this.id = medicalCard.getId();
        this.isDischarged = medicalCard.isDischarged();
        this.createTime = medicalCard.getCreateTime();
        this.updateTime = medicalCard.getUpdateTime();
        this.updatedBy = medicalCard.getUpdatedBy();
        this.patientId = medicalCard.getPatientId();
        this.doctorId = medicalCard.getDoctorId();

        this.doctorNameEN = doctorAccDetails.getNameEN();
        this.doctorSurnameEN = doctorAccDetails.getSurnameEN();
        this.doctorNameUA = doctorAccDetails.getNameUA();
        this.doctorSurnameUA = doctorAccDetails.getSurnameUA();

        this.updatedByNameEN = updatedByAccDetails.getNameEN();
        this.updatedBySurnameEN = updatedByAccDetails.getSurnameEN();
        this.updatedByNameUA = updatedByAccDetails.getNameUA();
        this.updatedBySurnameUA = updatedByAccDetails.getSurnameUA();

        this.specializationNameEN = specialization.getNameEN();
        this.specializationNameUA = specialization.getNameUA();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedCardDoctorBean bean = (MedCardDoctorBean) o;
        return isDischarged == bean.isDischarged && Objects.equals(id, bean.id) && Objects.equals(createTime, bean.createTime) && Objects.equals(updateTime, bean.updateTime) && Objects.equals(updatedBy, bean.updatedBy) && Objects.equals(patientId, bean.patientId) && Objects.equals(doctorId, bean.doctorId) && Objects.equals(updatedByNameEN, bean.updatedByNameEN) && Objects.equals(updatedBySurnameEN, bean.updatedBySurnameEN) && Objects.equals(updatedByNameUA, bean.updatedByNameUA) && Objects.equals(updatedBySurnameUA, bean.updatedBySurnameUA) && Objects.equals(doctorNameEN, bean.doctorNameEN) && Objects.equals(doctorSurnameEN, bean.doctorSurnameEN) && Objects.equals(doctorNameUA, bean.doctorNameUA) && Objects.equals(doctorSurnameUA, bean.doctorSurnameUA) && Objects.equals(specializationNameEN, bean.specializationNameEN) && Objects.equals(specializationNameUA, bean.specializationNameUA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isDischarged, createTime, updateTime, updatedBy, patientId, doctorId, updatedByNameEN, updatedBySurnameEN, updatedByNameUA, updatedBySurnameUA, doctorNameEN, doctorSurnameEN, doctorNameUA, doctorSurnameUA, specializationNameEN, specializationNameUA);
    }
}
