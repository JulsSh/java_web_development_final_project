package ru.eventify.eventify_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eventify.eventify_backend.dto.response.EventResponse;
import ru.eventify.eventify_backend.service.EventService;

import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {
        EventResponse body = eventService.getEventById(id);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<Page<EventResponse>> getAllEvents(@RequestParam(required = false) Instant from,
                                                            @RequestParam(required = false) Instant to,
                                                            Pageable pageable) {
        log.info("All available events: ");
        Page<EventResponse> events = eventService.getAllEvents(from, to, pageable);
        return ResponseEntity.ok(events);
    }
}
