package ru.eventify.eventify_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eventify.eventify_backend.dto.request.CreateBookingRequest;
import ru.eventify.eventify_backend.dto.response.BookingResponse;
import ru.eventify.eventify_backend.dto.response.EventResponse;
import ru.eventify.eventify_backend.entity.Booking;
import ru.eventify.eventify_backend.entity.Event;
import ru.eventify.eventify_backend.exception.BookingNotFoundException;
import ru.eventify.eventify_backend.exception.EventNotFoundException;
import ru.eventify.eventify_backend.exception.NotEnoughTicketsException;
import ru.eventify.eventify_backend.repository.BookingRepository;
import ru.eventify.eventify_backend.repository.EventRepository;

import java.time.Instant;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;

    private EventResponse toEventResponse(Event event) {
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCoverUrl(),
                event.getDateTime(),
                event.getTotalTickets(),
                event.getAvailableTickets()
        );
    }

    public BookingResponse getBookingById(Long id) {
        Booking savedBooking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        return new BookingResponse(savedBooking.getId(), toEventResponse(savedBooking.getEvent()), savedBooking.getCustomerEmail(),
                savedBooking.getTicketCount(), savedBooking.getCreatedAt(), savedBooking.getExpiryTime(), savedBooking.isConfirmed(), savedBooking.getTimezone());
    }


    public List<BookingResponse> geMyBookings(String email) {
        List<Booking> savedBooking = bookingRepository.findByCustomerEmail(email);
        return savedBooking.stream().map(booking ->
                new BookingResponse(
                        booking.getId(), toEventResponse(booking.getEvent()), booking.getCustomerEmail(), booking.getTicketCount(), booking.getCreatedAt(),
                        booking.getExpiryTime(), booking.isConfirmed(), booking.getTimezone())
        ).toList();
    }
@Transactional
    public BookingResponse createBooking(CreateBookingRequest request, String customerEmail) {

        Event event = eventRepository.findById(request.eventId()).orElseThrow(() -> new EventNotFoundException(request.eventId()));
        if (event.getAvailableTickets() < request.ticketCount()) {
            throw new NotEnoughTicketsException(request.ticketCount(), event.getAvailableTickets());
        }
         event.setAvailableTickets(event.getAvailableTickets() - request.ticketCount());
       Event savedEvent = eventRepository.save(event);

         Booking booking = new Booking();
         booking.setEvent(event);
         booking.setCustomerEmail(customerEmail);
         booking.setTicketCount(request.ticketCount());
         booking.setCreatedAt(Instant.now());
         booking.setConfirmed(false);
       Booking savedBooking =  bookingRepository.save(booking);

         return new BookingResponse(savedBooking.getId(), toEventResponse(savedEvent), savedBooking.getCustomerEmail(),
                  savedBooking.getTicketCount(), savedBooking.getCreatedAt(), savedBooking.getExpiryTime(), savedBooking.isConfirmed(), savedBooking.getTimezone());

    }


    public void confirmBooking(Long id){
        Booking booking = bookingRepository.findById(id).orElseThrow(()-> new BookingNotFoundException(id));
        booking.setConfirmed(true);
            bookingRepository.save(booking);
    }

    @Transactional
    public void deleteBooking(Long id){

        Booking booking = bookingRepository.findById(id).orElseThrow(()-> new BookingNotFoundException(id));
        Event event  = booking.getEvent();
        event.setAvailableTickets(event.getAvailableTickets() +booking.getTicketCount());
        eventRepository.save(event);
        bookingRepository.delete(booking);
    }
}
