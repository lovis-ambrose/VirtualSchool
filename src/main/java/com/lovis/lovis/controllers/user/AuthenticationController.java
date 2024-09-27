package com.lovis.lovis.controllers.user;

import com.lovis.lovis.entities.user.User;
import com.lovis.lovis.services.jwt.JwtService;
import com.lovis.lovis.services.user.AuthenticationService;
import com.lovis.lovis.services.user.dtos.LoginUserDto;
import com.lovis.lovis.services.user.dtos.RegisterUserDto;
import com.lovis.lovis.services.user.responses.LoginResponse;
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
    public ResponseEntity<User> signup(@RequestBody RegisterUserDto registerUser) {
        User registeredUser = authenticationService.signUp(registerUser);
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
