package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the surgery table in the database.
 *
 * @author Orest Dmyterko
 */
public class Surgery {
    private Long id;
    private String typeEN;
    private String typeUA;
    private String descriptionEN;
    private String descriptionUA;
    private boolean end;
    private LocalDateTime createTime;
    private Long createdBy;
    private Long servedBy;
    private Long medicalCardId;
}
