package com.revature.schoolz.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.revature.schoolz.dtos.requests.NewUserRequest;
import com.revature.schoolz.models.User;
import com.revature.schoolz.repositories.UserRepository;
import com.revature.schoolz.utils.custom_exceptions.DuplicateUsernameException;
import com.revature.schoolz.utils.custom_exceptions.InvalidPasswordException;
import com.revature.schoolz.utils.custom_exceptions.InvalidUsernameException;

import lombok.Data;

@Service
@Data
public class AuthService {
    private final UserRepository userRepository;

    public boolean signUp(NewUserRequest newUserRequest) {
        if (!isValidUsername(newUserRequest.getUsername())) {
            throw new InvalidUsernameException("Invalid username");
        }

        if (!isValidPassword(newUserRequest.getPassword())) {
            throw new InvalidPasswordException(
                    "Invalid password");
        }

        if (!isUniqueUsername(newUserRequest.getUsername())) {
            throw new DuplicateUsernameException("Username already exists"); // Username already exists
        }
        // Create a new user and save it to the database
        String encryptedPassword = BCrypt.hashpw(newUserRequest.getPassword(), BCrypt.gensalt());
        User newUser = new User(newUserRequest.getUsername(), encryptedPassword); // default role
        userRepository.save(newUser);
        return true;
    }

    public User signIn(NewUserRequest newUserRequest) {
        User user = userRepository.findByUsername(newUserRequest.getUsername());
        if (user == null) {
            throw new InvalidUsernameException("Invalid username or password"); // Authentication successful
        }

        if (!BCrypt.checkpw(newUserRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid username or password"); // Authentication failed
        }
        return user;
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    private boolean isUniqueUsername(String username) {
        return !userRepository.existsByUsername(username);
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_-]{3,20}$");
    }
}
