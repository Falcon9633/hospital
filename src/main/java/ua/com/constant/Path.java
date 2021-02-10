package ua.com.constant;

public class Path {
    public static final String REDIRECT = "redirect:/";
    public static final String LOGIN_PAGE = "login.jsp";
    public static final String ACCOUNT_REGISTRATION_ADMIN_PAGE = "/WEB-INF/jsp/admin/accountRegistrationAdmin.jsp";
    public static final String ACCOUNT_REGISTRATION_DOCTOR_PAGE = "/WEB-INF/jsp/admin/accountRegistrationDoctor.jsp";
    public static final String ACCOUNT_REGISTRATION_NURSE_PAGE = "/WEB-INF/jsp/admin/accountRegistrationNurse.jsp";
    public static final String ACCOUNT_REGISTRATION_PATIENT_PAGE = "/WEB-INF/jsp/admin/accountRegistrationPatient.jsp";

    public static final String ACCOUNT_REGISTRATION_ADMIN_COMMAND = "controller?command=accountRegistrationAdmin";
    public static final String ACCOUNT_REGISTRATION_DOCTOR_COMMAND = "controller?command=accountRegistrationDoctor";
    public static final String ACCOUNT_REGISTRATION_NURSE_COMMAND = "controller?command=accountRegistrationNurse";
    public static final String ACCOUNT_REGISTRATION_PATIENT_COMMAND = "controller?command=accountRegistrationPatient";
}
