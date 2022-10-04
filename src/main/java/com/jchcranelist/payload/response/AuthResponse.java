package com.jchcranelist.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String message;
    private String userName;
    private String accessToken;
    private String refreshToken;

    public AuthResponse() {
    }
}
