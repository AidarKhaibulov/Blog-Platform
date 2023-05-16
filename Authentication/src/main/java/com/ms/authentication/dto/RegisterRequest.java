package com.ms.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "firstname should not be empty")
    @Size(min =2, max=16,message = "invalid size")
    private String firstname;
    @NotEmpty(message = "lastname should not be empty")
    @Size(min =2, max=16,message = "too short or long name")
    private String lastname;
    @Email
    private String email;
    @NotEmpty(message = "password should not be empty")
    @Size(min =8, max=16,message = "invalid size")
    private String password;
}
