package ua.com.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents the account_details table in the database.
 *
 * @author Orest Dmyterko
 */
public class AccountDetails {
    private Long id;
    private String nameEN;
    private String surnameEN;
    private String nameUA;
    private String surnameUA;


    public AccountDetails() {
    }

    public AccountDetails(Long id, String nameEN, String surnameEN, String nameUA, String surnameUA) {
        this.id = id;
        this.nameEN = nameEN;
        this.surnameEN = surnameEN;
        this.nameUA = nameUA;
        this.surnameUA = surnameUA;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getSurnameEN() {
        return surnameEN;
    }

    public void setSurnameEN(String surnameEN) {
        this.surnameEN = surnameEN;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    public String getSurnameUA() {
        return surnameUA;
    }

    public void setSurnameUA(String surnameUA) {
        this.surnameUA = surnameUA;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", surnameEN='" + surnameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", surnameUA='" + surnameUA + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDetails that = (AccountDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(nameEN, that.nameEN) && Objects.equals(surnameEN, that.surnameEN) && Objects.equals(nameUA, that.nameUA) && Objects.equals(surnameUA, that.surnameUA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEN, surnameEN, nameUA, surnameUA);
    }
}
