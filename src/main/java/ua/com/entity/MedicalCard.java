package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the medical_card table in the database.
 *
 * @author Orest Dmyterko
 */
public class MedicalCard {
    private Long id;
    private boolean isDischarged;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long updatedBy;
    private Long patientId;
    private Long doctorId;

    public MedicalCard() {
    }

    public MedicalCard(Long updatedBy, Long patientId) {
        this.updatedBy = updatedBy;
        this.patientId = patientId;
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

    @Override
    public String toString() {
        return "MedicalCard{" +
                "id=" + id +
                ", isDischarged=" + isDischarged +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updatedBy=" + updatedBy +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                '}';
    }
}
