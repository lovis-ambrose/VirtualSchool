package com.lovis.lovis.services.user;

import com.lovis.lovis.entities.user.User;
import com.lovis.lovis.exceptions.response.ApiRequestResponse;
import com.lovis.lovis.exceptions.response.EmailAlreadyExistsResponse;
import com.lovis.lovis.repositories.users.UserRepository;
import com.lovis.lovis.services.user.dtos.LoginUserDto;
import com.lovis.lovis.services.user.dtos.RegisterUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signUp(RegisterUserDto register) {
        Optional<User> userExists = userRepository.findByEmail(register.getEmail());
        if (userExists.isPresent()) {
            throw new EmailAlreadyExistsResponse("User already exists");
        }
       User user = new User();
       user.setFullName(register.getFullName());
       user.setEmail(register.getEmail());
       user.setPassword(passwordEncoder.encode(register.getPassword()));
       return userRepository.save(user);
    }

    public User login(LoginUserDto login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        return userRepository.findByEmail(login.getEmail()).orElseThrow();
    }
}
