package ru.eventify.eventify_backend.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eventify.eventify_backend.dto.request.EventCreateRequest;
import ru.eventify.eventify_backend.dto.request.EventUpdateRequest;
import ru.eventify.eventify_backend.dto.response.EventResponse;
import ru.eventify.eventify_backend.service.EventService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/events")
public class AdminEventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponse> createEventByAdmin(@Valid @RequestBody EventCreateRequest request) {
        log.info("Create new event: ");
        EventResponse response = eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

   @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEventByAdmin(@PathVariable Long id, @Valid @RequestBody EventUpdateRequest request){
      EventResponse body=  eventService.updateEvent(request, id);
        return ResponseEntity.ok(body);
   }
}
