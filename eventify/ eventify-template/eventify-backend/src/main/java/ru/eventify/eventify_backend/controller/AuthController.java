package ru.eventify.eventify_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eventify.eventify_backend.dto.request.RegisterRequest;
import ru.eventify.eventify_backend.dto.response.UserResponse;
import ru.eventify.eventify_backend.entity.Role;
import ru.eventify.eventify_backend.entity.User;
import ru.eventify.eventify_backend.service.UserService;
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {
   private final UserService userService;
   @PostMapping("/register")
   public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
  log.info("Registration attempt for email: {}", registerRequest.email());
 User user =userService.register(registerRequest.email(), registerRequest.password(), Role.USER);
  UserResponse response= new UserResponse(user.getId(), user.getEmail(),user.getRole());
return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

}
