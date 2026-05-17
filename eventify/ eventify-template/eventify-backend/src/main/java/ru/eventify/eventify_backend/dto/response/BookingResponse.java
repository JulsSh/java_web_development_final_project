package ru.eventify.eventify_backend.dto.response;

import java.time.Instant;

public record BookingResponse(
        Long id,
        EventResponse event,
        String customerEmail,
        Integer ticketCount,
        Instant createdAt,
        Instant expiryTime,
        boolean confirmed,
        String timezone
) {
}
