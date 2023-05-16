package com.ms.authentication.services;

import com.ms.authentication.dto.UserDto;
import com.ms.authentication.models.User;
import com.ms.authentication.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User mapToUser(UserDto userDto) {
        return User.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }


    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }



    public ResponseEntity save(User user) {
        userRepository.save(user);
        return null;
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}