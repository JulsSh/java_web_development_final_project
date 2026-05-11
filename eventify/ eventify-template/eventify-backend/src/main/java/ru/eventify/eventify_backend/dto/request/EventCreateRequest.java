package ru.eventify.eventify_backend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record EventCreateRequest(
        @NotBlank @Size(max = 100) String title,
        String description,
        String coverUrl,
        @NotNull Instant dateTime,
        @Min(1) int totalTickets) {
}
