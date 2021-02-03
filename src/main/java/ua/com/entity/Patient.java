package ua.com.entity;

import java.time.LocalDate;

/**
 * This class represents the patient table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class Patient {
    private long id;
    private LocalDate birthday;
    private Account account;
}
