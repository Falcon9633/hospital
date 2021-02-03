package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the medicament table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class Medicament {
    private long id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private LocalDateTime createTime;
    private Account createdBy;
    private Account servedBy;
    private MedicalCard medicalCard;
}
