package ua.com.bean;

import ua.com.entity.AccountDetails;
import ua.com.entity.Procedure;
import ua.com.entity.Specialization;

import java.time.LocalDateTime;

public class ProcedureDoctorBean {
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

    private String doctorNameEN;
    private String doctorSurnameEN;
    private String doctorNameUA;
    private String doctorSurnameUA;

    private String specializationNameEN;
    private String specializationNameUA;

    private String servedByNameEN;
    private String servedBySurnameEN;
    private String servedByNameUA;
    private String servedBySurnameUA;

    public ProcedureDoctorBean() {
    }

    public ProcedureDoctorBean(Procedure procedure, AccountDetails doctorAccDetails, Specialization specialization){
        this.id = procedure.getId();
        this.nameEN = procedure.getNameEN();
        this.nameUA = procedure.getNameUA();
        this.descriptionEN = procedure.getDescriptionEN();
        this.descriptionUA = procedure.getDescriptionUA();
        this.end = procedure.isEnd();
        this.createTime = procedure.getCreateTime();
        this.createdBy = procedure.getCreatedBy();
        this.servedBy = procedure.getServedBy();
        this.medicalCardId = procedure.getMedicalCardId();

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

    public String getServedByNameEN() {
        return servedByNameEN;
    }

    public void setServedByNameEN(String servedByNameEN) {
        this.servedByNameEN = servedByNameEN;
    }

    public String getServedBySurnameEN() {
        return servedBySurnameEN;
    }

    public void setServedBySurnameEN(String servedBySurnameEN) {
        this.servedBySurnameEN = servedBySurnameEN;
    }

    public String getServedByNameUA() {
        return servedByNameUA;
    }

    public void setServedByNameUA(String servedByNameUA) {
        this.servedByNameUA = servedByNameUA;
    }

    public String getServedBySurnameUA() {
        return servedBySurnameUA;
    }

    public void setServedBySurnameUA(String servedBySurnameUA) {
        this.servedBySurnameUA = servedBySurnameUA;
    }

    @Override
    public String toString() {
        return "ProcedureDoctorBean{" +
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
                ", doctorNameEN='" + doctorNameEN + '\'' +
                ", doctorSurnameEN='" + doctorSurnameEN + '\'' +
                ", doctorNameUA='" + doctorNameUA + '\'' +
                ", doctorSurnameUA='" + doctorSurnameUA + '\'' +
                ", specializationNameEN='" + specializationNameEN + '\'' +
                ", specializationNameUA='" + specializationNameUA + '\'' +
                ", servedByNameEN='" + servedByNameEN + '\'' +
                ", servedBySurnameEN='" + servedBySurnameEN + '\'' +
                ", servedByNameUA='" + servedByNameUA + '\'' +
                ", servedBySurnameUA='" + servedBySurnameUA + '\'' +
                '}';
    }
}
