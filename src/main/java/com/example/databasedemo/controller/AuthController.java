package com.example.databasedemo.controller;

import com.example.databasedemo.entity.User;
import com.example.databasedemo.facade.AuthFacade;
import com.example.databasedemo.request.LoginRequest;
import com.example.databasedemo.request.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthFacade authFacade;

    public AuthController(
            AuthFacade authFacade
    ) {
        this.authFacade = authFacade;
    }

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return this.authFacade.getAllUsers();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return this.authFacade.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return this.authFacade.registerUser(signUpRequest);
    }
}
