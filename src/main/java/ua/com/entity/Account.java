package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the account table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class Account {
    private long id;
    private String login;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Account updatedBy;
    private boolean accountNonLocked;
    private Role role;
}
