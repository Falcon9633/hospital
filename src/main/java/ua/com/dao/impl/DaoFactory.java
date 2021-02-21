package ua.com.dao.impl;

import ua.com.dao.*;

public class DaoFactory {
    public static AccountDao getAccountDao(){
        AccountDetailsDao accountDetailsDao = new AccountDetailsDaoImpl();
        DoctorDao doctorDao = new DoctorDaoImpl();
        PatientDao patientDao = new PatientDaoImpl();
        return new AccountDaoImpl(accountDetailsDao, doctorDao, patientDao);
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

    public static DiagnosisDao getDiagnosisDao(){
        return new DiagnosisDaoImpl();
    }
}
