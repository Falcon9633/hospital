package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the medical_card table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class MedicalCard {
    private long id;
    private boolean isDischarged;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Account updatedBy;
    private Patient patient;
    private Doctor doctor;
}
