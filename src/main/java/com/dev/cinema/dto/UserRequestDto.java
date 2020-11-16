package com.dev.cinema.dto;

import com.dev.cinema.annotation.EmailConstraint;
import com.dev.cinema.annotation.PasswordConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@PasswordConstraint
@Data
public class UserRequestDto {
    @NotNull(message = "Email must be specified")
    @EmailConstraint
    private String email;
    @NotNull(message = "Password must be specified")
    @Size(min = 5, message = "Password has to have at least 5 characters")
    private String password;
    @NotNull(message = "Repeating password must be specified")
    @Size(min = 5, message = "Repeating password has to match "
            + "password and have at least 5 characters")
    private String repeatPassword;
}
