package com.example.gamestore;

import com.example.gamestore.services.game.GameService;
import com.example.gamestore.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.gamestore.constants.Commands.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final UserService userService;
    private final GameService gameService;


    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

//        RegisterUser|<email>|<password>|<confirmPassword>|<fullName>

        String input = SCANNER.nextLine();

        while(!input.equals(CLOSE)) {

            String[] inputArgs = input.split("\\|");
            String command = inputArgs[0];
            Object output = switch (command) {
                case REGISTER_USER -> this.userService.registerUser(inputArgs);
                case LOGGED_IN_USER -> this.userService.loginUser(inputArgs);
                case LOGGED_OUT_USER -> this.userService.logoutUser(inputArgs);
                case ADD_GAME-> this.gameService.addGame(inputArgs);
                case DELETE_GAME -> this.gameService.deleteGame(inputArgs);
                case EDIT_GAME -> this.gameService.editGame(inputArgs);
                case VIEW_ALL_GAMES-> this.gameService.allGames();
                case VIEW_GAME_DETAILS -> this.gameService.detailGame(inputArgs[1]);
//                case VIEW_GAMES_OWNED_BY-> this.userService.printOwnedGames();
                default -> "No command found";
            };

            System.out.println(output);

            input = SCANNER.nextLine();
        }

    }

}
