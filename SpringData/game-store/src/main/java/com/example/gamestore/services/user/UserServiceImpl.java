package com.example.gamestore.services.user;

import com.example.gamestore.domain.entities.User;
import com.example.gamestore.domain.models.OwnedGamesDto;
import com.example.gamestore.domain.models.UserLoginDto;
import com.example.gamestore.domain.models.UserLogoutDto;
import com.example.gamestore.domain.models.UserRegisterDto;
import com.example.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.gamestore.constants.ErrorMessages.*;

@Service
public class UserServiceImpl implements UserService {
    private User loggedInUser;
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] args) {
//      RegisterUser|<email>|<password>|<confirmPassword>|<fullName>
        final int argsLength = args.length;
        String email = argsLength > 1 ? args[1] : "";
        String password = argsLength > 2 ? args[2]: "";
        String confirmPassword = argsLength > 3 ? args[3] : "";
        String fullName = argsLength > 4 ? args[4] : "";

        UserRegisterDto userRegisterDto;

        try {
            userRegisterDto = new UserRegisterDto(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException ex) {
           return ex.getMessage();
        }

//        checking if the user already exists in the DB
        if (this.userRepository.findFirstByEmail(userRegisterDto.getEmail()).isPresent()) {
            return EMAIL_ALREADY_EXISTS;
        }

        final User user = this.modelMapper.map(userRegisterDto, User.class);

        if (this.userRepository.count() == 0) {
            user.setIsAdmin(true);
        } else {
            user.setIsAdmin(false);
        }

        this.userRepository.saveAndFlush(user);

        return userRegisterDto.successfullyRegisteredUser();
    }

    @Override
    public String loginUser(String[] args) {

        if(this.loggedInUser != null) return "User already logged";

        final int argsLength = args.length;
        String email = argsLength > 1 ? args[1] : "";
        String password = argsLength > 2 ? args[2]: "";
        String fullName = argsLength > 4 ? args[4] : "";

        Optional<User> loggedUser = this.userRepository.findFirstByEmail(email);
        if (loggedUser.isEmpty()) {
            return NO_LOGGED_USER;
        }

        UserLoginDto userLoginDto = new UserLoginDto(email, password, fullName);

        try {
            userLoginDto.validate(loggedUser.get().getPassword());

        }catch (IllegalArgumentException ex) {
            ex.getMessage();
        }
        this.loggedInUser = loggedUser.get();

        return userLoginDto.successfullyLogged();
    }
    @Override
    public String logoutUser(String[] args) {

        if(this.loggedInUser == null) return "No user logged";

        final int argsLength = args.length;
        String email = argsLength > 1 ? args[1] : "";
        String password = argsLength > 2 ? args[2]: "";
        String fullName = argsLength > 4 ? args[4] : "";

        UserLogoutDto userLogoutDto = new UserLogoutDto(email, password, fullName);

        this.loggedInUser = null;

        return userLogoutDto.successfullyLoggedOut();

    }
    @Override
    public boolean isLoggedUserAdmin() {
        return this.loggedInUser != null && this.loggedInUser.getIsAdmin();
    }

    @Override
    public List<OwnedGamesDto> printOwnedGames(User loggedInUser) {
        return userRepository.findAllByUser(loggedInUser.getId()).stream()
                .map(game -> modelMapper.map(game, OwnedGamesDto.class))
                .collect(Collectors.toList());

    }
}
