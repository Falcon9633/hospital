package ua.com.bean;

import ua.com.entity.Account;

import java.time.LocalDateTime;

public class NurseAccDetailsBean {
    private Long id;
    private String login;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean locked;
    private long updatedBy;
    private int roleId;
    private int localeId;

    private String nameEN;
    private String surnameEN;
    private String nameUA;
    private String surnameUA;

    public NurseAccDetailsBean() {
    }

    public NurseAccDetailsBean(Account account) {
        this.id = account.getId();
        this.login = account.getLogin();
        this.password = account.getPassword();
        this.email = account.getEmail();
        this.createTime = account.getCreateTime();
        this.updateTime = account.getUpdateTime();
        this.locked = account.isLocked();
        this.updatedBy = account.getUpdatedBy();
        this.roleId = account.getRoleId();
        this.localeId = account.getLocaleId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getLocaleId() {
        return localeId;
    }

    public void setLocaleId(int localeId) {
        this.localeId = localeId;
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

    @Override
    public String toString() {
        return "NurseAccDetailsBean{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", locked=" + locked +
                ", updatedBy=" + updatedBy +
                ", roleId=" + roleId +
                ", localeId=" + localeId +
                ", nameEN='" + nameEN + '\'' +
                ", surnameEN='" + surnameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", surnameUA='" + surnameUA + '\'' +
                '}';
    }
}