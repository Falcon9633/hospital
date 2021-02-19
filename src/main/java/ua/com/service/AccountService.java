package ua.com.service;

import ua.com.entity.Role;

import java.sql.SQLException;
import java.time.LocalDate;

public interface AccountService {
    boolean registerAccount(String login, Long updatedBy, Role role, String nameEN, String surnameEN, String nameUA,
                            String surnameUA, String email, Integer specializationId, LocalDate birthday);
}
