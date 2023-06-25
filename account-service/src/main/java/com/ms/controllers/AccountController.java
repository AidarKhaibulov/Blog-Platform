package com.ms.controllers;

import com.ms.dto.UserEditDto;
import com.ms.entities.User;
import com.ms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final UserService userService;
    @Autowired
    private final RestTemplate restTemplate;

    @GetMapping()
    public User getUserInfo(HttpServletRequest request) {
        String username = getUsernameByTokenFromHeader(request);
        return userService.getUserInfo(username).orElseThrow();
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(HttpServletRequest request, @Valid @RequestBody UserEditDto user) {
        String username = getUsernameByTokenFromHeader(request);
        User existingUser = userService.getUserByUsername(username).orElseThrow();

        userService.updateUser(existingUser, user);
        log.info("User updated successfully");
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        String username = getUsernameByTokenFromHeader(request);
        User existingUser = userService.getUserByUsername(username).orElseThrow();

        userService.deleteUser(existingUser);
        log.info("User deleted successfully");
        return ResponseEntity.ok("User deleted successfully");
    }


    private String getUsernameByTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String host = "localhost";
        int port = 8080;
        String callToAuthService = String.format("http://%s:%d/auth/getCurrentUserEmail/%s", host, port, token);
        ResponseEntity<String> username = restTemplate.getForEntity(callToAuthService, String.class);
        return username.getBody();
    }
}

