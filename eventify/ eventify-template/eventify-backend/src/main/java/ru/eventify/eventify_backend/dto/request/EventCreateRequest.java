package ru.eventify.eventify_backend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record EventCreateRequest(
        @NotBlank @Size(max = 255) String title,
        String description,
        String coverUrl,
        @NotNull Instant dateTime,
        @NotNull @Min(1) Integer totalTickets
      //  @NotNull @Min(0) Integer availableTickets
) {
}
