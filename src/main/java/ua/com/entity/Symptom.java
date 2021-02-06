package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the symptom table in the database.
 *
 * @author Orest Dmyterko
 */
public class Symptom {
    private Long id;
    private String patientComplainsEN;
    private String patientComplainsUA;
    private String doctorObservationEN;
    private String doctorObservationUA;
    private LocalDateTime createTime;
    private Long createdBy;
    private Long medicalCardId;
}
