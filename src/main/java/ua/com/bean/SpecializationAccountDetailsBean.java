package ua.com.bean;

import ua.com.entity.AccountDetails;

import java.time.LocalDateTime;

public class SpecializationAccountDetailsBean {
    private Integer id;
    private String nameEN;
    private String nameUA;
    private LocalDateTime updateTime;
    private String updatedByNameEN;
    private String updatedBySurnameEN;
    private String updatedByNameUA;
    private String updatedBySurnameUA;

    public SpecializationAccountDetailsBean(AccountDetails updatedByAccDetails) {
        this.updatedByNameEN = updatedByAccDetails.getNameEN();
        this.updatedBySurnameEN = updatedByAccDetails.getSurnameEN();
        this.updatedByNameUA = updatedByAccDetails.getNameUA();
        this.updatedBySurnameUA = updatedByAccDetails.getSurnameUA();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "SpecializationAccountDetails{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", updateTime=" + updateTime +
                ", accountNameEN='" + updatedByNameEN + '\'' +
                ", accountSurnameEN='" + updatedBySurnameEN + '\'' +
                ", accountNameUA='" + updatedByNameUA + '\'' +
                ", accountSurnameUA='" + updatedBySurnameUA + '\'' +
                '}';
    }
}
