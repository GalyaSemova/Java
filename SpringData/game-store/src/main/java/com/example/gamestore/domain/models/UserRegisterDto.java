package com.example.gamestore.domain.models;

import lombok.*;

import java.util.regex.Pattern;

import static com.example.gamestore.constants.ErrorMessages.*;
import static com.example.gamestore.constants.Validations.EMAIL_PATTERN;
import static com.example.gamestore.constants.Validations.PASSWORD_PATTERN;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserRegisterDto {

    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;

    public UserRegisterDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }
//Password validation
    private void validate() {
        if (!Pattern.matches(EMAIL_PATTERN, this.email)) {
            throw new IllegalArgumentException(INVALID_EMAIL);
        }
//        o Password – length must be at least 6 symbols and must contain at least 1 uppercase, 1 lowercase
//letter and 1 digit.
        if (!Pattern.matches(PASSWORD_PATTERN, this.password)) {
            throw new IllegalArgumentException(INVALID_PASSWORD);
        }


        if(!this.password.equals(this.confirmPassword)) {
            throw new IllegalArgumentException(PASS_MISS_MATCH);
        }

    }

    public String successfullyRegisteredUser() {
        return fullName + " was registered.";
    }
}
