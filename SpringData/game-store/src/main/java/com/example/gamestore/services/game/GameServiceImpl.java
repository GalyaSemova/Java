package com.example.gamestore.services.game;

import com.example.gamestore.domain.entities.Game;
import com.example.gamestore.domain.models.AllGameDto;
import com.example.gamestore.domain.models.DetailGameDto;
import com.example.gamestore.domain.models.GameDto;
import com.example.gamestore.domain.models.GameEditDto;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper modelMapper = new ModelMapper();
    private GameRepository gameRepository;
    private UserService userService;

    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public String addGame(String[] arguments) {
//        • AddGame|<title>|<price>|<size>|<trailer>|<thubnailURL>|<description>|<releaseDate>
        int length = arguments.length;
        if (!this.userService.isLoggedUserAdmin()) {
            return "Logged in user is not admin!";
        }

        final String title = length > 1 ? arguments[1] : "";
        final BigDecimal price = length > 2 ? new BigDecimal(arguments[2]) : BigDecimal.ZERO;
        final float size  = length > 3 ? Float.parseFloat(arguments[3]) : Float.parseFloat("0.0");
        final String trailer= length > 4 ? arguments[4] : "";;
        final String thumbnailURL = length > 5 ? arguments[5] : "";;
        final String description  = length > 6 ? arguments[6] : "";;
        final LocalDate releaseDate = LocalDate.now();

        GameDto gameDto = new GameDto(title, price, size, trailer, thumbnailURL, description, releaseDate);

        Game gameToBeSaved = this.modelMapper.map(gameDto, Game.class);

        gameRepository.saveAndFlush(gameToBeSaved);

        return gameDto.successfullyAddedGame();
    }

    @Override
    public String deleteGame(String[] arguments) {
        if (!this.userService.isLoggedUserAdmin()) return "Logged user is not admin.";

        Optional<Game> gameToBeDeleted = this.gameRepository.findById(Long.valueOf(arguments[1]));

        if (gameToBeDeleted.isEmpty()) return "No Such game";

        this.gameRepository.delete(gameToBeDeleted.get());

        return "Deleted " + gameToBeDeleted.get().getTitle();
    }

    @Override
    public String editGame(String[] arguments) {
//        EditGame|<id>|<values> - A game should be edited in case of valid id.

        if (!this.userService.isLoggedUserAdmin()) {
            return "Logged in user is not admin!";
        }
        Optional<Game> gameToBeEdited = this.gameRepository.findById(Long.valueOf(arguments[1]));

        if (gameToBeEdited.isEmpty()) return "No Such game";

        Map<String, String> updatingArguments = new HashMap<>();

        for (int i = 2; i < arguments.length; i++) {
            String[] argumentsForUpdate = arguments[i].split("=");
            updatingArguments.put(argumentsForUpdate[0], argumentsForUpdate[1]);
        }

        GameEditDto gameEditDto = this.modelMapper.map(gameToBeEdited.get(), GameEditDto.class);

        gameEditDto.updateFields(updatingArguments);

        Game gameToBeSaved = this.modelMapper.map(gameEditDto, Game.class);

        this.gameRepository.saveAndFlush(gameToBeSaved);

        return "Edited " + gameEditDto.getTitle();
    }

    @Override
    public List<AllGameDto> allGames() {
        return gameRepository.findAll().stream()
                .map(g -> modelMapper.map(g, AllGameDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DetailGameDto detailGame(String title) {
        Game game = gameRepository.findGameByTitle(title);
        DetailGameDto detailGameDto = null;
        if (game != null) {
            detailGameDto = modelMapper.map(game, DetailGameDto.class);
        }
        return detailGameDto;
    }


}
