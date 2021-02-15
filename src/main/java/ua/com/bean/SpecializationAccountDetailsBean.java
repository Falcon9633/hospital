package ua.com.bean;

import java.time.LocalDateTime;

public class SpecializationAccountDetailsBean {
    private Integer id;
    private String nameEN;
    private String nameUA;
    private LocalDateTime updateTime;
    private String accountNameEN;
    private String accountSurnameEN;
    private String accountNameUA;
    private String accountSurnameUA;

    public SpecializationAccountDetailsBean() {
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

    public String getAccountNameEN() {
        return accountNameEN;
    }

    public void setAccountNameEN(String accountNameEN) {
        this.accountNameEN = accountNameEN;
    }

    public String getAccountSurnameEN() {
        return accountSurnameEN;
    }

    public void setAccountSurnameEN(String accountSurnameEN) {
        this.accountSurnameEN = accountSurnameEN;
    }

    public String getAccountNameUA() {
        return accountNameUA;
    }

    public void setAccountNameUA(String accountNameUA) {
        this.accountNameUA = accountNameUA;
    }

    public String getAccountSurnameUA() {
        return accountSurnameUA;
    }

    public void setAccountSurnameUA(String accountSurnameUA) {
        this.accountSurnameUA = accountSurnameUA;
    }

    @Override
    public String toString() {
        return "SpecializationAccountDetails{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", updateTime=" + updateTime +
                ", accountNameEN='" + accountNameEN + '\'' +
                ", accountSurnameEN='" + accountSurnameEN + '\'' +
                ", accountNameUA='" + accountNameUA + '\'' +
                ", accountSurnameUA='" + accountSurnameUA + '\'' +
                '}';
    }
}
