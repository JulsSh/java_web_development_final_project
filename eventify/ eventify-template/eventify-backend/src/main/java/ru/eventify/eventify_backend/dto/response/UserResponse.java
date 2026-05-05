package ru.eventify.eventify_backend.dto.response;

import ru.eventify.eventify_backend.entity.Role;

public record UserResponse(
        Long id,
        String email,
        Role role
) {
}
