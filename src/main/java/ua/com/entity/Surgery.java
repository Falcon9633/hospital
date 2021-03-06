package ua.com.entity;

/**
 * This class represents the surgery table in the database.
 *
 * @author Orest Dmyterko
 */
public class Surgery extends Assignment {
    public Surgery() {
    }

    public Surgery(String nameEN, String nameUA, String descriptionEN, String descriptionUA, Long createdBy, Long empId, Long medicalCardId) {
        super(nameEN, nameUA, descriptionEN, descriptionUA, createdBy, empId, medicalCardId);
    }
}
