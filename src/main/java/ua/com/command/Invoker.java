package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.command.admin.*;
import ua.com.command.doctor.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Initializes and holds all commands. Invokes concrete command.
 *
 * @author Orest Dmyterko
 */
public class Invoker {
    private static final Logger LOGGER = LogManager.getLogger(Invoker.class);
    private static final Map<String, Command> commandContainer = new HashMap<>();

    static {
        // common commands
        commandContainer.put("unknownCommand", new UnknownCommand());
        commandContainer.put("login", new LoginCommand());
        commandContainer.put("logout", new LogoutCommand());
        commandContainer.put("changeLocale", new ChangeLocaleCommand());
        // admin commands
        commandContainer.put("accountRegistrationAdmin", new AccountRegistrationAdminCommand());
        commandContainer.put("accountRegistrationDoctor", new AccountRegistrationDoctorCommand());
        commandContainer.put("accountRegistrationNurse", new AccountRegistrationNurseCommand());
        commandContainer.put("accountRegistrationPatient", new AccountRegistrationPatientCommand());
        commandContainer.put("registerAccount", new RegisterAccountCommand());
        commandContainer.put("doctorSpecialization", new DoctorSpecializationCommand());
        commandContainer.put("createSpecialization", new CreateSpecializationCommand());
        commandContainer.put("editSpecialization", new EditSpecializationCommand());
        commandContainer.put("usersDoctors", new UsersDoctorsCommand());
        commandContainer.put("editDoctor", new EditDoctorCommand());
        commandContainer.put("usersPatients", new UsersPatientsCommand());
        commandContainer.put("editPatient", new EditPatientCommand());
        commandContainer.put("administratePatientMedicalCards", new AdministratePatientMedicalCardsCommand());
        commandContainer.put("createMedicalCard", new CreateMedicalCardCommand());
        commandContainer.put("medicalCardSetDoctor", new MedicalCardSetDoctorCommand());
        // doctor command
        commandContainer.put("doctorCurrentMedicalCards", new DoctorCurrentMedicalCardsCommand());
        commandContainer.put("doctorDiagnosesMedicalCard", new DoctorDiagnosesMedicalCardCommand());
        commandContainer.put("createDiagnosis", new CreateDiagnosisCommand());
        commandContainer.put("doctorMedicamentsMedicalCard", new DoctorMedicamentsMedicalCardCommand());
        commandContainer.put("createMedicament", new CreateMedicamentCommand());
        commandContainer.put("editMedicament", new EditMedicamentCommand());
    }

    /**
     * Invokes concrete command, if command does not exist invokes 'unknownCommand' command.
     *
     * @param commandName command which must be invoked
     * @param req         an HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp        an HttpServletResponse object that contains the response the servlet sends to the client
     * @return The path to which a request dispatcher must be forwarded
     */
    public String invoke(String commandName, HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("invoke starts");
        if (commandName == null || !commandContainer.containsKey(commandName)) {
            LOGGER.trace("Command '{}' not found", commandName);
            return commandContainer.get("unknownCommand").execute(req, resp);
        }

        return commandContainer.get(commandName).execute(req, resp);
    }
}
