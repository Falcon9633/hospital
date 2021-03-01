package ua.com.entity;


/**
 * This class represents the procedure table in the database.
 *
 * @author Orest Dmyterko
 */
public class Procedure extends Assignment {

    public Procedure() {
    }

    public Procedure(String nameEN, String nameUA, String descriptionEN, String descriptionUA, Long createdBy, Long empId, Long medicalCardId) {
        super(nameEN, nameUA, descriptionEN, descriptionUA, createdBy, empId, medicalCardId);
    }
}
