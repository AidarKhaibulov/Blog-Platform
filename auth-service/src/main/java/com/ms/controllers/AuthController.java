package com.ms.controllers;

import com.ms.models.User;
import com.ms.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return ResponseEntity.ok(authService.saveUser(user));

    }
    @GetMapping("/token")
    public String getToken(User user){
        return authService.generateToken(user.getName());
    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token ){
        authService.validateToken(token);
        return "Token is valid";
    }
}
