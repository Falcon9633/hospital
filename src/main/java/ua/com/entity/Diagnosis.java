package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the diagnosis table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class Diagnosis {
    private long id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private LocalDateTime createTime;
    private Account createdBy;
    private MedicalCard medicalCard;
}
