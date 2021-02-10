package ua.com.entity;

/**
 * This class represents the specialization table in the database.
 *
 * @author Orest Dmyterko
 */
public class Specialization {
    private Integer id;
    private String nameEN;
    private String nameUA;

    public Specialization() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                '}';
    }
}
