package com.ms.controller;

import com.ms.dto.ApiResponse;
import com.ms.dto.AuthRequest;
import com.ms.dto.RegisterRequest;
import com.ms.dto.UserDto;
import com.ms.exceptions.AuthenticationFailedException;
import com.ms.exceptions.EmailOrUsernameAlreadyExistsException;
import com.ms.service.AuthService;
import com.ms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    /*@PostMapping("/register")
    public UserDto addNewUser(@Valid @RequestBody RegisterRequest registerRequest) throws RuntimeException{
        return authService.register(registerRequest);
    }*/
    @PostMapping("/register")
    public ApiResponse addNewUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            UserDto userDto = authService.register(registerRequest);
            return ApiResponse.success(userDto);
        } catch (EmailOrUsernameAlreadyExistsException e) {
            return ApiResponse.error("Email or Username already exists");
        }
    }


    @PostMapping("/authenticate")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        } else {
            throw new AuthenticationFailedException("Authentication failed");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
