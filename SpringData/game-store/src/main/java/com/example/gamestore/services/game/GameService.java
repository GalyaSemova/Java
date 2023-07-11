package com.example.gamestore.services.game;

import com.example.gamestore.domain.entities.Game;
import com.example.gamestore.domain.models.AllGameDto;
import com.example.gamestore.domain.models.DetailGameDto;

import java.util.List;

public interface GameService {
    String addGame (String[] arguments);
    String deleteGame (String[] arguments);
    String editGame (String[] arguments);

    List<AllGameDto> allGames();
    DetailGameDto detailGame(String title);
}
