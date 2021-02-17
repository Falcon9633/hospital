package ua.com.constant;

public class Path {
    public static final String REDIRECT = "redirect:/";
    public static final String LOGIN_PAGE = "login.jsp";
    public static final String ACCOUNT_REGISTRATION_ADMIN_PAGE = "/WEB-INF/jsp/admin/accountRegistrationAdmin.jsp";
    public static final String ACCOUNT_REGISTRATION_DOCTOR_PAGE = "/WEB-INF/jsp/admin/accountRegistrationDoctor.jsp";
    public static final String ACCOUNT_REGISTRATION_NURSE_PAGE = "/WEB-INF/jsp/admin/accountRegistrationNurse.jsp";
    public static final String ACCOUNT_REGISTRATION_PATIENT_PAGE = "/WEB-INF/jsp/admin/accountRegistrationPatient.jsp";
    public static final String DOCTOR_SPECIALIZATION_PAGE = "/WEB-INF/jsp/admin/doctorSpecialization.jsp";
    public static final String USERS_DOCTORS_PAGE = "/WEB-INF/jsp/admin/usersDoctors.jsp";
    public static final String USERS_PATIENTS_PAGE = "/WEB-INF/jsp/admin/usersPatients.jsp";

    public static final String ACCOUNT_REGISTRATION_ADMIN_COMMAND = "controller?command=accountRegistrationAdmin";
    public static final String ACCOUNT_REGISTRATION_DOCTOR_COMMAND = "controller?command=accountRegistrationDoctor";
    public static final String ACCOUNT_REGISTRATION_NURSE_COMMAND = "controller?command=accountRegistrationNurse";
    public static final String ACCOUNT_REGISTRATION_PATIENT_COMMAND = "controller?command=accountRegistrationPatient";
    public static final String DOCTOR_SPECIALIZATION_COMMAND = "controller?command=doctorSpecialization";
    public static final String USERS_DOCTORS_COMMAND = "controller?command=usersDoctors";
    public static final String USERS_PATIENTS_COMMAND = "controller?command=usersPatients";
}
