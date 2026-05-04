package ru.eventify.eventify_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.eventify.eventify_backend.entity.Role;
import ru.eventify.eventify_backend.entity.User;
import ru.eventify.eventify_backend.exception.EmailAlreadyExistsException;
import ru.eventify.eventify_backend.repository.UserRepository;

import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String email, String rawPassword, Role role) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException(email);
        }
        User user =new User();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        log.info("User registered successfully: id={}, email={}", savedUser.getId(), savedUser.getEmail());
        return savedUser;
    }

    public Optional<User> findByEmail(String email) {
            return userRepository.findByEmail(email);
        }

}
