package ua.com.dao.impl;

import ua.com.dao.*;

public class DaoFactory {
    public static AccountDao getAccountDao(){
        return new AccountDaoImpl();
    }

    public static AccountDetailsDao getAccountDetailsDao(){
        return new AccountDetailsDaoImpl();
    }

    public static DoctorDao getDoctorDao(){
        return new DoctorDaoImpl();
    }

    public static MedicalCardDao getMedicalCardDao(){
        return new MedicalCardDaoImpl();
    }

    public static PatientDao getPatientDao(){
        return new PatientDaoImpl();
    }

    public static SpecializationDao getSpecializationDao(){
        return new SpecializationDaoImpl();
    }
}
