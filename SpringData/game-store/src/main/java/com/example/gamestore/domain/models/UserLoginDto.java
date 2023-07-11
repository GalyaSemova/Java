package com.example.gamestore.domain.models;

import lombok.Getter;
import lombok.Setter;

import static com.example.gamestore.constants.ErrorMessages.PASS_MISS_MATCH;

@Getter
@Setter
public class UserLoginDto {
    private String email;
    private String password;
    private String fullName;

    public UserLoginDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;


    }

    public void validate(String realPassword) {
        if (this.password.equals(realPassword)) {
            throw new IllegalArgumentException(PASS_MISS_MATCH);
        }
    }

    public String successfullyLogged() {
        return "Successfully logged in " + this.email;
    }
}
