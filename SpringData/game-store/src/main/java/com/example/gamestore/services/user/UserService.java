package com.example.gamestore.services.user;

import com.example.gamestore.domain.entities.User;
import com.example.gamestore.domain.models.OwnedGamesDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    String registerUser (String[] args);
    String loginUser (String[] args);
    String logoutUser (String[] args);
    boolean isLoggedUserAdmin();

    List<OwnedGamesDto> printOwnedGames(User loggedInUser);
}
