package com.ms.service;

import com.ms.dto.UserDto;
import com.ms.dto.UserEditDto;
import com.ms.entities.User;
import com.ms.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    public Optional<User> getUserInfo(String username){
        return userRepository.findByUsername(username);
    }
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .build();
    }

    public void updateUser(User existingUser, @Valid UserEditDto newUser) {
        existingUser.setEmail(newUser.getEmail());
        existingUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(existingUser);
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
