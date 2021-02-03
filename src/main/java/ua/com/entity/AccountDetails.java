package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the account_details table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class AccountDetails {
    private long id;
    private String nameEN;
    private String nameUA;
    private String surnameEN;
    private String surnameUA;
    private LocalDateTime updateTime;
    private Account account;
}
