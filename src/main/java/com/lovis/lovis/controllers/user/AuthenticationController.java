package com.lovis.lovis.controllers.user;

import com.lovis.lovis.entities.user.User;
import com.lovis.lovis.services.jwt.JwtService;
import com.lovis.lovis.services.user.AuthenticationService;
import com.lovis.lovis.services.user.dtos.LoginUserDto;
import com.lovis.lovis.services.user.dtos.RegisterUserDto;
import com.lovis.lovis.services.user.responses.LoginResponse;
import com.lovis.lovis.services.user.responses.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/v1/user/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("signup")
    public ResponseEntity<RegisterResponse> signup(@RequestBody RegisterUserDto registerUser) {
        User user = authenticationService.signUp(registerUser);
        RegisterResponse registeredUser = new RegisterResponse();
        registeredUser.setId(user.getId());
        registeredUser.setFullName(user.getFullName());
        registeredUser.setEmail(user.getEmail());
        registeredUser.setCreatedAt(user.getCreatedAt());
        registeredUser.setUpdatedAt(user.getUpdatedAt());
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUser) {
        User authenticatedUser = authenticationService.login(loginUser);
        String token = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
