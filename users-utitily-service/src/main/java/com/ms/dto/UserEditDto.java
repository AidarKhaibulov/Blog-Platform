package com.ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEditDto {
    @Email
    private String email;
    @Size(min = 8,max = 16)
    private String password;
}
