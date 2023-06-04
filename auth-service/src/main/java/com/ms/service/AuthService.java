package com.ms.service;

import com.ms.dto.RegisterRequest;
import com.ms.dto.UserDto;
import com.ms.entities.User;
import com.ms.exceptions.EmailOrUsernameAlreadyExistsException;
import com.ms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    public UserDto register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        String username = user.getUsername();
        String email = user.getEmail();

        if (userRepository.existsByEmail(email) || userRepository.existsByUsername(username))
            throw new EmailOrUsernameAlreadyExistsException("Username or email is taken!");
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user);
            log.info("User registered {}", user);
            return userService.mapToUserDto(user);
        }
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
