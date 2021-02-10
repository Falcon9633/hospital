package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the account table in the database.
 *
 * @author Orest Dmyterko
 */
public class Account {
    private Long id;
    private String login;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean locked;
    private long updatedBy;
    private int roleId;
    private int localeId;

    public Account() {
    }

    public Account(String login, long updatedBy, int roleId) {
        this.login = login;
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

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updatedBy=" + updatedBy +
                ", locked=" + locked +
                ", roleId=" + roleId +
                ", localeId=" + localeId +
                '}';
    }
}
