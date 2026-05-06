package ru.eventify.eventify_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.eventify.eventify_backend.entity.User;
import ru.eventify.eventify_backend.exception.InvalidCredentialsException;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(String email,String rawPassword){
       User user= userService.findByEmail(email)
        .orElseThrow(InvalidCredentialsException::new);

       if(!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
           throw new InvalidCredentialsException();
        }
        log.info("User logged in successfully: email={}", email);
        return jwtService.generateToken(email);
        }
}
