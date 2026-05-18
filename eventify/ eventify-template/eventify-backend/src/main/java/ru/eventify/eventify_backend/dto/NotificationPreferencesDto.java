package ru.eventify.eventify_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record NotificationPreferencesDto(
        boolean notifyNewEvents,
        boolean notifyUpcoming,
        @Min(1) @Max(24) Integer notifyBeforeHours
) {
}