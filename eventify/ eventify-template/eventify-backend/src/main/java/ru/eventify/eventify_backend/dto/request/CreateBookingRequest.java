package ru.eventify.eventify_backend.dto.request;

public record CreateBookingRequest(
        Long eventId,
        Integer ticketCount
) {
}
