package ua.com.bean;

public class DoctorAccDetailsBean {
    private Long id;
    private Integer specializationId;
    private String nameEN;
    private String surnameEN;
    private String nameUA;
    private String surnameUA;

    public DoctorAccDetailsBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
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
        return "DoctorAccDetailsBean{" +
                "id=" + id +
                ", specializationId=" + specializationId +
                ", nameEN='" + nameEN + '\'' +
                ", surnameEN='" + surnameEN + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", surnameUA='" + surnameUA + '\'' +
                '}';
    }
}
