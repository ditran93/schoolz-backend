package com.revature.schoolz.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.schoolz.dtos.requests.NewUserRequest;
import com.revature.schoolz.dtos.responses.UserSession;
import com.revature.schoolz.models.User;
import com.revature.schoolz.services.AuthService;
import com.revature.schoolz.services.SessionService;
import com.revature.schoolz.utils.custom_exceptions.InvalidSessionException;
import com.revature.schoolz.utils.custom_exceptions.SignUpException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class AuthController {
    private final AuthService authService;
    private final SessionService sessionService;

    @PostMapping("/sign-up")
    public ResponseEntity<NewUserRequest> register(@RequestBody NewUserRequest newUserRequest) {
        if (!authService.signUp(newUserRequest)) {
            throw new SignUpException("Failed to sign up");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserSession> login(@RequestBody NewUserRequest newUserRequest) {
        User user = authService.signIn(newUserRequest);
        UserSession userSession = sessionService.createSession(user);
        return ResponseEntity.ok(userSession);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> logout() {
        sessionService.invalidateSession();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserSession> getSession() {
        UserSession userSession = sessionService.getSession();
        if (userSession == null) {
            throw new InvalidSessionException("No active session found");
        }
        return ResponseEntity.ok(userSession);
    }
}
