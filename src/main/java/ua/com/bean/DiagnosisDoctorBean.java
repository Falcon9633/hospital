package ua.com.bean;

import ua.com.entity.AccountDetails;
import ua.com.entity.Specialization;

import java.time.LocalDateTime;
import java.util.Objects;

public class DiagnosisDoctorBean {
    private Long id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private LocalDateTime createTime;
    private Long createdBy;
    private Long medicalCardId;

    private String doctorNameEN;
    private String doctorSurnameEN;
    private String doctorNameUA;
    private String doctorSurnameUA;

    private String specializationNameEN;
    private String specializationNameUA;

    public DiagnosisDoctorBean(AccountDetails doctorAccDetails, Specialization specialization) {
        this.doctorNameEN = doctorAccDetails.getNameEN();
        this.doctorSurnameEN = doctorAccDetails.getSurnameEN();
        this.doctorNameUA = doctorAccDetails.getNameUA();
        this.doctorSurnameUA = doctorAccDetails.getSurnameUA();

        this.specializationNameEN = specialization.getNameEN();
        this.specializationNameUA = specialization.getNameUA();
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
        return "DiagnosisDoctorBean{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", descriptionEN='" + descriptionEN + '\'' +
                ", descriptionUA='" + descriptionUA + '\'' +
                ", createTime=" + createTime +
                ", createdBy=" + createdBy +
                ", medicalCardId=" + medicalCardId +
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
        DiagnosisDoctorBean that = (DiagnosisDoctorBean) o;
        return Objects.equals(id, that.id) && Objects.equals(nameEN, that.nameEN) && Objects.equals(nameUA, that.nameUA) && Objects.equals(descriptionEN, that.descriptionEN) && Objects.equals(descriptionUA, that.descriptionUA) && Objects.equals(createTime, that.createTime) && Objects.equals(createdBy, that.createdBy) && Objects.equals(medicalCardId, that.medicalCardId) && Objects.equals(doctorNameEN, that.doctorNameEN) && Objects.equals(doctorSurnameEN, that.doctorSurnameEN) && Objects.equals(doctorNameUA, that.doctorNameUA) && Objects.equals(doctorSurnameUA, that.doctorSurnameUA) && Objects.equals(specializationNameEN, that.specializationNameEN) && Objects.equals(specializationNameUA, that.specializationNameUA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEN, nameUA, descriptionEN, descriptionUA, createTime, createdBy, medicalCardId, doctorNameEN, doctorSurnameEN, doctorNameUA, doctorSurnameUA, specializationNameEN, specializationNameUA);
    }
}
