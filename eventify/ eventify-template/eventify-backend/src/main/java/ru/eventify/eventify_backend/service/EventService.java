package ru.eventify.eventify_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.eventify.eventify_backend.dto.request.EventCreateRequest;
import ru.eventify.eventify_backend.dto.request.EventUpdateRequest;
import ru.eventify.eventify_backend.dto.response.EventResponse;
import ru.eventify.eventify_backend.entity.Event;
import ru.eventify.eventify_backend.exception.EventNotFoundException;
import ru.eventify.eventify_backend.repository.EventRepository;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventResponse createEvent(EventCreateRequest eventCreateRequest) {
        Event event = new Event();
        event.setTitle(eventCreateRequest.title());
        event.setDescription(eventCreateRequest.description());
        event.setCoverUrl(eventCreateRequest.coverUrl());
        event.setDateTime(eventCreateRequest.dateTime());
        event.setTotalTickets(eventCreateRequest.totalTickets());
        Event savedEvent = eventRepository.save(event);
        log.info("Event created successfully: {}", savedEvent.getTitle());

        return new EventResponse(savedEvent.getId(), savedEvent.getTitle(), savedEvent.getDescription(),
                savedEvent.getDateTime(), savedEvent.getTotalTickets(), savedEvent.getTotalTickets(), savedEvent.getCoverUrl());
    }

    public EventResponse getEventById(Long id) {

        Event savedEvent = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(String.valueOf(id)));
        return new EventResponse(savedEvent.getId(), savedEvent.getTitle(), savedEvent.getDescription(),
                savedEvent.getDateTime(), savedEvent.getTotalTickets(), savedEvent.getTotalTickets(), savedEvent.getCoverUrl());
    }

    public EventResponse updateEvent(EventUpdateRequest request, Long id) {
        Event savedEvent = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(String.valueOf(id)));
        if (request.title() != null) {
            savedEvent.setTitle(request.title());
        }
        if (request.description() != null) {
            savedEvent.setDescription(request.description());
        }
        if (request.totalTickets() != null) {
            savedEvent.setTotalTickets(request.totalTickets());
        }
        if (request.dateTime() != null) {
            savedEvent.setDateTime(request.dateTime());
        }
        if (request.coverUrl() != null) {
            savedEvent.setCoverUrl(request.coverUrl());
        }

        eventRepository.save(savedEvent);
        return new EventResponse(savedEvent.getId(), savedEvent.getTitle(), savedEvent.getDescription(),
                savedEvent.getDateTime(), savedEvent.getTotalTickets(), savedEvent.getTotalTickets(), savedEvent.getCoverUrl());
    }

    public void deleteEvent(Long id) {
        Event saved = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(String.valueOf(id)));
        eventRepository.deleteById(id);
    }

    public Page<EventResponse> getAllEvents(Instant from, Instant to, Pageable pageable) {
        if (from == null && to == null) {
            return eventRepository.findAll(pageable).map(event -> new EventResponse(event.getId(), event.getTitle(),
                    event.getDescription(), event.getDateTime(), event.getTotalTickets(), event.getTotalTickets(),
                    event.getCoverUrl()));
        } else {
            return eventRepository.findByDateTimeBetween(from, to, pageable).map(event -> new EventResponse(event.getId(), event.getTitle(),
                    event.getDescription(), event.getDateTime(), event.getTotalTickets(), event.getTotalTickets(),
                    event.getCoverUrl()));
        }
    }
}
