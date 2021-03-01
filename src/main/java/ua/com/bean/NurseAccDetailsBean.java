package ua.com.bean;

import ua.com.entity.Account;
import ua.com.entity.AccountDetails;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public NurseAccDetailsBean(Account account, AccountDetails accountDetails) {
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

        this.nameEN = accountDetails.getNameEN();
        this.surnameEN = accountDetails.getSurnameEN();
        this.nameUA = accountDetails.getNameUA();
        this.surnameUA = accountDetails.getSurnameUA();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NurseAccDetailsBean bean = (NurseAccDetailsBean) o;
        return locked == bean.locked && updatedBy == bean.updatedBy && roleId == bean.roleId && localeId == bean.localeId && Objects.equals(id, bean.id) && Objects.equals(login, bean.login) && Objects.equals(password, bean.password) && Objects.equals(email, bean.email) && Objects.equals(createTime, bean.createTime) && Objects.equals(updateTime, bean.updateTime) && Objects.equals(nameEN, bean.nameEN) && Objects.equals(surnameEN, bean.surnameEN) && Objects.equals(nameUA, bean.nameUA) && Objects.equals(surnameUA, bean.surnameUA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, createTime, updateTime, locked, updatedBy, roleId, localeId, nameEN, surnameEN, nameUA, surnameUA);
    }
}
