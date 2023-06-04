package com.ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @Size(min = 2, max = 16, message = "invalid username size")
    private String username;
    @Email
    private String email;
    @Size(min = 8, max = 64, message = "invalid password size")
    private String password;
}
