package ru.eventify.eventify_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eventify.eventify_backend.dto.response.UserResponse;
import ru.eventify.eventify_backend.entity.User;
import ru.eventify.eventify_backend.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Authentication authentication) {

        String email = authentication.getName();
        log.info("Profile requested by: {}", email);
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Authenticated user not found in DB: " + email));
        UserResponse response = new UserResponse(user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(response);
    }
}