package com.lovis.lovis.services.user.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private long expiresIn;
}
