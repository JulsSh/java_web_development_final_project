package ru.eventify.eventify_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eventify.eventify_backend.dto.NotificationPreferencesDto;
import ru.eventify.eventify_backend.entity.NotificationPreferences;
import ru.eventify.eventify_backend.repository.NotificationPreferencesRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationPreferencesService {

    private final NotificationPreferencesRepository repository;

    private NotificationPreferencesDto toDto(NotificationPreferences entity) {
        return new NotificationPreferencesDto(
                entity.isNotifyNewEvents(),
                entity.isNotifyUpcoming(),
                entity.getNotifyBeforeHours()
        );
    }

    public NotificationPreferencesDto getPreferences(String userEmail) {
        return repository.findByUserEmail(userEmail)
                .map(this::toDto)
                .orElse(new NotificationPreferencesDto(false, false, 24));
    }

    @Transactional
    public NotificationPreferencesDto updatePreferences(String userEmail, NotificationPreferencesDto dto) {
        NotificationPreferences entity = repository.findByUserEmail(userEmail)
                .orElse(new NotificationPreferences());
        entity.setUserEmail(userEmail);
        entity.setNotifyNewEvents(dto.notifyNewEvents());
        entity.setNotifyUpcoming(dto.notifyUpcoming());
        entity.setNotifyBeforeHours(dto.notifyBeforeHours());
        NotificationPreferences saved = repository.save(entity);
        log.info("Notification preferences updated for {}", userEmail);
        return toDto(saved);
    }

    @Transactional
    public void deletePreferences(String userEmail) {
        repository.findByUserEmail(userEmail)
                .ifPresent(repository::delete);
        log.info("Notification preferences deleted for {}", userEmail);
    }
}