package com.lovis.lovis.services.user.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegisterResponse {
    private int id;
    private String fullName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
