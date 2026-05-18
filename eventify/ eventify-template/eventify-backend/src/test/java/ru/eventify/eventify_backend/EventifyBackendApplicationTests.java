package ru.eventify.eventify_backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.eventify.eventify_backend.dto.request.EventCreateRequest;
import ru.eventify.eventify_backend.dto.request.EventUpdateRequest;
import ru.eventify.eventify_backend.dto.response.EventResponse;
import ru.eventify.eventify_backend.entity.Event;
import ru.eventify.eventify_backend.exception.EventNotFoundException;
import ru.eventify.eventify_backend.repository.EventRepository;
import ru.eventify.eventify_backend.service.EventService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EventifyBackendApplicationTests {

	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventService eventService;

	// Вспомогательный метод — собирает готовый Event для тестов (чтобы не повторять).
	private Event sampleEvent() {
		Event event = new Event();
		event.setId(1L);
		event.setTitle("Spring Conf");
		event.setDescription("A conference");
		event.setCoverUrl("http://img/cover.png");
		event.setDateTime(Instant.parse("2026-09-01T18:00:00Z"));
		event.setTotalTickets(100);
		event.setAvailableTickets(100);
		return event;
	}

	// --- createEvent ---------------------------------------------------------

	@Test
	void createEvent_savesEventAndSetsAvailableTicketsEqualToTotal() {
		EventCreateRequest request = new EventCreateRequest(
				"Spring Conf", "A conference", "http://img/cover.png",
				Instant.parse("2026-09-01T18:00:00Z"), 100);

		when(eventRepository.save(any(Event.class)))
				.thenAnswer(invocation -> {
					Event e = invocation.getArgument(0);
					e.setId(1L);
					return e;
				});

		EventResponse response = eventService.createEvent(request);

		assertThat(response.id()).isEqualTo(1L);
		assertThat(response.title()).isEqualTo("Spring Conf");
		assertThat(response.totalTickets()).isEqualTo(100);

		assertThat(response.availableTickets()).isEqualTo(100);

		verify(eventRepository).save(any(Event.class));
	}

	@Test
	void getEventById_returnsEvent_whenExists() {
		when(eventRepository.findById(1L)).thenReturn(Optional.of(sampleEvent()));

		EventResponse response = eventService.getEventById(1L);

		assertThat(response.id()).isEqualTo(1L);
		assertThat(response.title()).isEqualTo("Spring Conf");
	}

	@Test
	void getEventById_throwsException_whenNotFound() {
		when(eventRepository.findById(99L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> eventService.getEventById(99L))
				.isInstanceOf(EventNotFoundException.class);
	}

	@Test
	void updateEvent_updatesOnlyNonNullFields() {
		Event existing = sampleEvent();
		when(eventRepository.findById(1L)).thenReturn(Optional.of(existing));
		when(eventRepository.save(any(Event.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));


		EventUpdateRequest request = new EventUpdateRequest(
				"New Title", null, null, null, null);

		EventResponse response = eventService.updateEvent(request, 1L);

		assertThat(response.title()).isEqualTo("New Title");          // изменилось
		assertThat(response.description()).isEqualTo("A conference"); // не тронуто
		assertThat(response.totalTickets()).isEqualTo(100);           // не тронуто
	}

	@Test
	void updateEvent_throwsException_whenNotFound() {
		when(eventRepository.findById(99L)).thenReturn(Optional.empty());

		EventUpdateRequest request = new EventUpdateRequest(
				"X", null, null, null, null);

		assertThatThrownBy(() -> eventService.updateEvent(request, 99L))
				.isInstanceOf(EventNotFoundException.class);
	}

	@Test
	void deleteEvent_deletes_whenExists() {
		when(eventRepository.findById(1L)).thenReturn(Optional.of(sampleEvent()));

		eventService.deleteEvent(1L);

		verify(eventRepository).deleteById(1L);
	}

	@Test
	void deleteEvent_throwsException_whenNotFound() {
		when(eventRepository.findById(99L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> eventService.deleteEvent(99L))
				.isInstanceOf(EventNotFoundException.class);
		verify(eventRepository, never()).deleteById(anyLong());
	}


	@Test
	void getAllEvents_usesFindAll_whenNoDateFilter() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Event> page = new PageImpl<>(List.of(sampleEvent()));
		when(eventRepository.findAll(pageable)).thenReturn(page);

		Page<EventResponse> result = eventService.getAllEvents(null, null, pageable);

		assertThat(result.getTotalElements()).isEqualTo(1);
		assertThat(result.getContent().get(0).title()).isEqualTo("Spring Conf");
		verify(eventRepository).findAll(pageable);
		verify(eventRepository, never())
				.findByDateTimeBetween(any(), any(), any());
	}

	@Test
	void getAllEvents_usesDateFilter_whenFromAndToProvided() {
		Pageable pageable = PageRequest.of(0, 10);
		Instant from = Instant.parse("2026-01-01T00:00:00Z");
		Instant to = Instant.parse("2026-12-31T00:00:00Z");
		Page<Event> page = new PageImpl<>(List.of(sampleEvent()));
		when(eventRepository.findByDateTimeBetween(from, to, pageable)).thenReturn(page);

		Page<EventResponse> result = eventService.getAllEvents(from, to, pageable);

		assertThat(result.getTotalElements()).isEqualTo(1);
		verify(eventRepository).findByDateTimeBetween(from, to, pageable);
		verify(eventRepository, never()).findAll(any(Pageable.class));
	}
}
