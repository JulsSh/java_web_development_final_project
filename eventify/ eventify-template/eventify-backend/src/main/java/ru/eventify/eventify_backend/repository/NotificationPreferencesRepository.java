package ru.eventify.eventify_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eventify.eventify_backend.entity.NotificationPreferences;

import java.util.Optional;

public interface NotificationPreferencesRepository extends JpaRepository<NotificationPreferences, Long> {
    Optional<NotificationPreferences> findByUserEmail(String userEmail);
}
