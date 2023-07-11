package com.example.gamestore.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogoutDto {
    private String email;
    private String password;
    private String fullName;

    public UserLogoutDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public String successfullyLoggedOut() {
        return "User " + this.fullName + " successfully logged out";
    }

}
