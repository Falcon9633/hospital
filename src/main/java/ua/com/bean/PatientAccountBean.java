package ua.com.bean;

import ua.com.entity.AccountDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientAccountBean {
    private Long id;

    private String nameEN;
    private String surnameEN;
    private String nameUA;
    private String surnameUA;

    private String email;
    private LocalDate birthday;
    private boolean locked;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String updatedByNameEN;
    private String updatedBySurnameEN;
    private String updatedByNameUA;
    private String updatedBySurnameUA;

    public PatientAccountBean(AccountDetails accountDetails, AccountDetails updatedByAccDetails) {
        this.nameEN = accountDetails.getNameEN();
        this.surnameEN = accountDetails.getSurnameEN();
        this.nameUA = accountDetails.getNameUA();
        this.surnameUA = accountDetails.getSurnameUA();

        this.updatedByNameEN = updatedByAccDetails.getNameEN();
        this.updatedBySurnameEN = updatedByAccDetails.getSurnameEN();
        this.updatedByNameUA = updatedByAccDetails.getNameUA();
        this.updatedBySurnameUA = updatedByAccDetails.getSurnameUA();
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    @Override
    public String toString() {
        return "PatientAccountBean{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", surnameEN='" + surnameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", surnameUA='" + surnameUA + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", locked=" + locked +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateByNameEN='" + updatedByNameEN + '\'' +
                ", updateBySurnameEN='" + updatedBySurnameEN + '\'' +
                ", updateByNameUA='" + updatedByNameUA + '\'' +
                ", updateBySurnameUA='" + updatedBySurnameUA + '\'' +
                '}';
    }
}
