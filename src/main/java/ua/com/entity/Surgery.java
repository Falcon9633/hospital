package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the surgery table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class Surgery {
    private long id;
    private String typeEN;
    private String typeUA;
    private String descriptionEN;
    private String descriptionUA;
    private LocalDateTime createTime;
    private Account createdBy;
    private Account servedBy;
    private MedicalCard medicalCard;
}
