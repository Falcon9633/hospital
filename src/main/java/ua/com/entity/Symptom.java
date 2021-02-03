package ua.com.entity;

import java.time.LocalDateTime;

/**
 * This class represents the symptom table in the database
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class Symptom {
    private long id;
    private String patientComplainsEN;
    private String patientComplainsUA;
    private String doctorObservationEN;
    private String doctorObservationUA;
    private LocalDateTime createTime;
    private Account createdBy;
    private MedicalCard medicalCard;
}
