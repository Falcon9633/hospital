package ua.com.bean;

import ua.com.entity.AccountDetails;
import ua.com.entity.Specialization;

import java.time.LocalDateTime;
import java.util.Objects;

public class DoctorAccountBean {
    private Long id;
    private Integer specializationId;

    private String nameEN;
    private String surnameEN;
    private String nameUA;
    private String surnameUA;

    private String email;
    private boolean locked;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String updatedByNameEN;
    private String updatedBySurnameEN;
    private String updatedByNameUA;
    private String updatedBySurnameUA;

    private int patientCount;

    private String specializationNameEN;
    private String specializationNameUA;

    public DoctorAccountBean() {
    }

    public DoctorAccountBean(DoctorAccDetailsBean doctorAccDetailsBean, AccountDetails updatedByAccDetails, Specialization specialization) {
        this.id = doctorAccDetailsBean.getId();
        this.specializationId = doctorAccDetailsBean.getSpecializationId();
        this.nameEN = doctorAccDetailsBean.getNameEN();
        this.surnameEN = doctorAccDetailsBean.getSurnameEN();
        this.nameUA = doctorAccDetailsBean.getNameUA();
        this.surnameUA = doctorAccDetailsBean.getSurnameUA();

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

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getSurnameEN() {
        return surnameEN;
    }

    public void setSurnameEN(String surnameEN) {
        this.surnameEN = surnameEN;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    public String getSurnameUA() {
        return surnameUA;
    }

    public void setSurnameUA(String surnameUA) {
        this.surnameUA = surnameUA;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
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
        return "DoctorAccountBean{" +
                "id=" + id +
                ", specializationId=" + specializationId +
                ", nameEN='" + nameEN + '\'' +
                ", surnameEN='" + surnameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", surnameUA='" + surnameUA + '\'' +
                ", email='" + email + '\'' +
                ", locked=" + locked +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updatedByNameEN='" + updatedByNameEN + '\'' +
                ", updatedBySurnameEN='" + updatedBySurnameEN + '\'' +
                ", updatedByNameUA='" + updatedByNameUA + '\'' +
                ", updatedBySurnameUA='" + updatedBySurnameUA + '\'' +
                ", patientCount=" + patientCount +
                ", specializationNameEN='" + specializationNameEN + '\'' +
                ", specializationNameUA='" + specializationNameUA + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAccountBean bean = (DoctorAccountBean) o;
        return locked == bean.locked && patientCount == bean.patientCount && Objects.equals(id, bean.id) && Objects.equals(specializationId, bean.specializationId) && Objects.equals(nameEN, bean.nameEN) && Objects.equals(surnameEN, bean.surnameEN) && Objects.equals(nameUA, bean.nameUA) && Objects.equals(surnameUA, bean.surnameUA) && Objects.equals(email, bean.email) && Objects.equals(createTime, bean.createTime) && Objects.equals(updateTime, bean.updateTime) && Objects.equals(updatedByNameEN, bean.updatedByNameEN) && Objects.equals(updatedBySurnameEN, bean.updatedBySurnameEN) && Objects.equals(updatedByNameUA, bean.updatedByNameUA) && Objects.equals(updatedBySurnameUA, bean.updatedBySurnameUA) && Objects.equals(specializationNameEN, bean.specializationNameEN) && Objects.equals(specializationNameUA, bean.specializationNameUA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specializationId, nameEN, surnameEN, nameUA, surnameUA, email, locked, createTime, updateTime, updatedByNameEN, updatedBySurnameEN, updatedByNameUA, updatedBySurnameUA, patientCount, specializationNameEN, specializationNameUA);
    }
}
