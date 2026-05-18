package ru.eventify.eventify_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.eventify.eventify_backend.dto.NotificationPreferencesDto;
import ru.eventify.eventify_backend.service.NotificationPreferencesService;

@Slf4j
@RestController
@RequestMapping("/user/notifications")
@RequiredArgsConstructor
public class NotificationPreferencesController {

    private final NotificationPreferencesService service;

    @GetMapping
    public ResponseEntity<NotificationPreferencesDto> getPreferences(Authentication authentication) {
        log.info("Get notification preferences for {}", authentication.getName());
        NotificationPreferencesDto body = service.getPreferences(authentication.getName());
        return ResponseEntity.ok(body);
    }

    @PutMapping
    public ResponseEntity<NotificationPreferencesDto> updatePreferences(
            @Valid @RequestBody NotificationPreferencesDto request,
            Authentication authentication) {
        log.info("Update notification preferences for {}", authentication.getName());
        NotificationPreferencesDto body = service.updatePreferences(authentication.getName(), request);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePreferences(Authentication authentication) {
        log.info("Delete notification preferences for {}", authentication.getName());
        service.deletePreferences(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}