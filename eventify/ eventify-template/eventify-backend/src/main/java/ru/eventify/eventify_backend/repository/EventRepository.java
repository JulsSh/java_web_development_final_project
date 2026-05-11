package ru.eventify.eventify_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.eventify.eventify_backend.entity.Event;
import org.springframework.data.domain.Pageable;
import java.time.Instant;

public interface EventRepository extends JpaRepository<Event, Long> {
    public Page<Event> findByDateTimeBetween(Instant from, Instant to, Pageable pageable);
}
