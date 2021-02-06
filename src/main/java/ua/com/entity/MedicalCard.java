package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the medical_card table in the database.
 *
 * @author Orest Dmyterko
 */
public class MedicalCard {
    private Long id;
    private boolean isDischarged;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long updatedBy;
    private Long patientId;
    private Long doctorId;
}
