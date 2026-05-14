package ru.eventify.eventify_backend.controller;


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
@RequestMapping("/admin")
public class AdminEventController {
    private final EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<EventResponse> createEvenByAdmin(@RequestBody EventCreateRequest request) {
        log.info("Create new event: ");
        EventResponse response = eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/events/{id}")
    public void deleteEventById(@PathVariable Long id){
 eventService.deleteEvent(id);
    }

   @PutMapping("/events/{id}")
    public ResponseEntity<EventResponse> updateEventByAdmin(@PathVariable Long id, @RequestBody EventUpdateRequest request){
      EventResponse body=  eventService.updateEvent(request, id);
        return ResponseEntity.ok(body);
   }
}
