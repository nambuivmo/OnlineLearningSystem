package com.onlinelearningsystem.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginResponse {
    String message;
    Boolean status;
    String token;
    String refreshToken;

    public LoginResponse(String message,Boolean status) {
        this.status = status;
        this.message = message;
    }
}
