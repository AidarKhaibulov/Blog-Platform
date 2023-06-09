package com.ms.service;

import com.ms.dto.UserDto;
import com.ms.entities.User;
import com.ms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUserInfo(){
        //TODO: return user's data
        return null;
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .build();
    }
}
