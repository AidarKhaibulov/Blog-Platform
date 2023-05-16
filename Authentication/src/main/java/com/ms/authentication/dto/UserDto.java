package com.ms.authentication.dto;


import com.ms.authentication.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {
    private Long id;
    @NotEmpty(message = "firstname should not be empty")
    @Size(min =2, max=16,message = "invalid size")
    private String firstname;
    @NotEmpty(message = "lastname should not be empty")
    @Size(min =2, max=16,message = "invalid size")
    private String lastname;
    @Email
    private String email;
    @NotEmpty(message = "password should not be empty")
    @Size(min =8, max=16,message = "invalid size")
    private String password;
    @NotNull(message = "role should not be empty")
    private User.Role role;


}