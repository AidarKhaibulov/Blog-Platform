package com.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean success;
    private Object data;
    private String errorMessage;

    public static ApiResponse success(Object data) {
        return new ApiResponse(true, data, null);
    }

    public static ApiResponse error(String errorMessage) {
        return new ApiResponse(false, null, errorMessage);
    }
}
