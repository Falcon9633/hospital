package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the account_details table in the database.
 *
 * @author Orest Dmyterko
 */
public class AccountDetails {
    private Long id;
    private String nameEN;
    private String surnameEN;
    private String nameUA;
    private String surnameUA;
    private String email;
    private LocalDateTime updateTime;

    public AccountDetails() {
    }

    public AccountDetails(Long id, String nameEN, String surnameEN, String nameUA, String surnameUA, String email) {
        this.id = id;
        this.nameEN = nameEN;
        this.surnameEN = surnameEN;
        this.nameUA = nameUA;
        this.surnameUA = surnameUA;
        this.email = email;
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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", surnameEN='" + surnameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", surnameUA='" + surnameUA + '\'' +
                ", email='" + email + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
