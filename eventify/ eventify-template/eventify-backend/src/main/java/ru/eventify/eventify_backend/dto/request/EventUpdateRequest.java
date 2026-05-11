package ru.eventify.eventify_backend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record EventUpdateRequest(

        @Size(max = 100) String title,
        String description,
        String coverUrl,
        Instant dateTime,
        @Min(1) Integer totalTickets) {

}
