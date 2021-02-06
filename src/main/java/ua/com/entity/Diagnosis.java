package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the diagnosis table in the database.
 *
 * @author Orest Dmyterko
 */
public class Diagnosis {
    private Long id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private LocalDateTime createTime;
    private Long createdBy;
    private Long medicalCardId;
}
