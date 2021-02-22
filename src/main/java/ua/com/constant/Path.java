package ua.com.constant;

public class Path {
    public static final String REDIRECT = "redirect:/";
    // common pages
    public static final String LOGIN_PAGE = "login.jsp";
    public static final String MEDICAL_CARD_DIAGNOSES_PAGE = "/WEB-INF/jsp/common/medicalCardDiagnoses.jsp";
    public static final String MEDICAL_CARD_MEDICAMENTS_PAGE = "/WEB-INF/jsp/common/medicalCardMedicaments.jsp";
    // admin pages
    public static final String ACCOUNT_REGISTRATION_ADMIN_PAGE = "/WEB-INF/jsp/admin/accountRegistrationAdmin.jsp";
    public static final String ACCOUNT_REGISTRATION_DOCTOR_PAGE = "/WEB-INF/jsp/admin/accountRegistrationDoctor.jsp";
    public static final String ACCOUNT_REGISTRATION_NURSE_PAGE = "/WEB-INF/jsp/admin/accountRegistrationNurse.jsp";
    public static final String ACCOUNT_REGISTRATION_PATIENT_PAGE = "/WEB-INF/jsp/admin/accountRegistrationPatient.jsp";
    public static final String DOCTOR_SPECIALIZATION_PAGE = "/WEB-INF/jsp/admin/doctorSpecialization.jsp";
    public static final String USERS_DOCTORS_PAGE = "/WEB-INF/jsp/admin/usersDoctors.jsp";
    public static final String USERS_PATIENTS_PAGE = "/WEB-INF/jsp/admin/usersPatients.jsp";
    public static final String ADMINISTRATE_PATIENT_MEDICAL_CARDS_PAGE = "/WEB-INF/jsp/admin/administratePatientMedicalCards.jsp";
    // doctor pages
    public static final String DOCTOR_CURRENT_MEDICAL_CARDS_PAGE = "/WEB-INF/jsp/doctor/doctorCurrentMedicalCards.jsp";
    // admin commands
    public static final String ACCOUNT_REGISTRATION_ADMIN_COMMAND = "controller?command=accountRegistrationAdmin";
    public static final String ACCOUNT_REGISTRATION_DOCTOR_COMMAND = "controller?command=accountRegistrationDoctor";
    public static final String ACCOUNT_REGISTRATION_NURSE_COMMAND = "controller?command=accountRegistrationNurse";
    public static final String ACCOUNT_REGISTRATION_PATIENT_COMMAND = "controller?command=accountRegistrationPatient";
    public static final String DOCTOR_SPECIALIZATION_COMMAND = "controller?command=doctorSpecialization";
    public static final String USERS_DOCTORS_COMMAND = "controller?command=usersDoctors";
    public static final String USERS_PATIENTS_COMMAND = "controller?command=usersPatients";
    public static final String ADMINISTRATE_PATIENT_MEDICAL_CARDS_COMMAND = "controller?command=administratePatientMedicalCards&patient_id=";
    // doctor commands
    public static final String DOCTOR_CURRENT_MEDICAL_CARDS_COMMAND = "controller?command=doctorCurrentMedicalCards";
    public static final String DOCTOR_DIAGNOSES_MEDICAL_CARD_COMMAND =
            "controller?command=doctorDiagnosesMedicalCard&medical_card_id=%d&patient_id=%d";
    public static final String DOCTOR_MEDICAMENTS_MEDICAL_CARD_COMMAND =
            "controller?command=doctorMedicamentsMedicalCard&medical_card_id=%d&patient_id=%d";

    public static final String LOCALE_COOKIE = "locale";
}
