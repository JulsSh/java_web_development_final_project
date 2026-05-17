package ru.eventify.eventify_backend.dto.request;

import jakarta.validation.constraints.Min;

public record UpdateBookingRequest(
        @Min(1) Integer ticketCount
) {
}
