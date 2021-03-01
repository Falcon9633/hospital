package ua.com.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents the account table in the database.
 *
 * @author Orest Dmyterko
 */
public class Account {
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

    public Account() {
    }

    public Account(String login, String password, String email, long updatedBy, int roleId) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.updatedBy = updatedBy;
        this.roleId = roleId;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", locked=" + locked +
                ", updatedBy=" + updatedBy +
                ", roleId=" + roleId +
                ", localeId=" + localeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return locked == account.locked && updatedBy == account.updatedBy && roleId == account.roleId && localeId == account.localeId && Objects.equals(id, account.id) && Objects.equals(login, account.login) && Objects.equals(password, account.password) && Objects.equals(email, account.email) && Objects.equals(createTime, account.createTime) && Objects.equals(updateTime, account.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, createTime, updateTime, locked, updatedBy, roleId, localeId);
    }
}
