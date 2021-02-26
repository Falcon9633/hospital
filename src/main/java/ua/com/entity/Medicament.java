package ua.com.entity;


/**
 * This class represents the medicament table in the database.
 *
 * @author Orest Dmyterko
 */
public class Medicament extends Assignment {
    public Medicament() {
    }

    public Medicament(String nameEN, String nameUA, String descriptionEN, String descriptionUA, Long id, Long empId, Long medicalCardId) {
        super(nameEN, nameUA, descriptionEN, descriptionUA, id, empId, medicalCardId);
    }
}
