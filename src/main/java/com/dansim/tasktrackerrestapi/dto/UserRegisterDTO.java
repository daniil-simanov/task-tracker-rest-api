package com.dansim.tasktrackerrestapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Must be in email format")
    private String email;
    @NotEmpty(message = "Password must not be empty")
    private String password;
}
