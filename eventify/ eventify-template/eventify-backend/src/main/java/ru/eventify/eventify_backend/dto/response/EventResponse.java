package ru.eventify.eventify_backend.dto.response;

import java.time.Instant;

public record EventResponse(
        Long id,
        String title,
        String description,
        String coverUrl,
        Instant dateTime,
        Integer totalTickets,
        Integer availableTickets

) {
}
