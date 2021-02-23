package ua.com.dao.impl;

import ua.com.dao.*;

public class DaoFactory {
    public static AccountDao getAccountDao(){
        return new AccountDaoImpl(getAccountDetailsDao(), getDoctorDao(), getPatientDao());
    }

    public static AccountDetailsDao getAccountDetailsDao(){
        return new AccountDetailsDaoImpl();
    }

    public static DoctorDao getDoctorDao(){
        return new DoctorDaoImpl();
    }

    public static MedicalCardDao getMedicalCardDao(){
        return new MedicalCardDaoImpl(getMedicamentDao(), getProcedureDao(), getSurgeryDao());
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

    public static MedicamentDao getMedicamentDao(){
        return new MedicamentDaoImpl();
    }

    public static ProcedureDao getProcedureDao() {
        return new ProcedureDaoImpl();
    }

    public static SurgeryDao getSurgeryDao() {
        return new SurgeryDaoImpl();
    }
}
