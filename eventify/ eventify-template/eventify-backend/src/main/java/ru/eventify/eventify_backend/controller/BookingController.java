package ru.eventify.eventify_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.eventify.eventify_backend.dto.request.CreateBookingRequest;
import ru.eventify.eventify_backend.dto.request.UpdateBookingRequest;
import ru.eventify.eventify_backend.dto.response.BookingResponse;
import ru.eventify.eventify_backend.service.BookingService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        log.info("Get event by  id: {}", id);
        BookingResponse body = bookingService.getBookingById(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBookingById(@PathVariable Long id, @Valid @RequestBody UpdateBookingRequest request) {
        log.info("Update booking by id {}", id);
        BookingResponse body = bookingService.updateBooking(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable Long id) {
        log.info("Remove booking by id {}", id);
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request, Authentication authentication) {
        log.info("Create booking {} ", request);

        BookingResponse bookingResponse = bookingService.createBooking(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponse);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getMyBookings(Authentication authentication) {
        String email = authentication.getName();
        List<BookingResponse> bookings = bookingService.geMyBookings(email);
        return ResponseEntity.ok(bookings);
    }
}
