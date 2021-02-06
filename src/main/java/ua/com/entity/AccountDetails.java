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
    private String nameUA;
    private String surnameEN;
    private String surnameUA;
    private LocalDateTime updateTime;
}
