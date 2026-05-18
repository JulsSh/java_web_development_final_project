package ru.eventify.eventify_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eventify.eventify_backend.dto.response.BookingResponse;
import ru.eventify.eventify_backend.service.BookingService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/bookings")
public class AdminBookingController {
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<Page<BookingResponse>> getAllBookings(Pageable pageable) {
        log.info("Get all available bookings: ");
        Page<BookingResponse> bookings = bookingService.getAllBookings(pageable);
        return ResponseEntity.ok(bookings);
    }
    @PutMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmBooking(@PathVariable Long id){
        log.info("Confirm booking: {}", id);
        bookingService.confirmBooking(id);
        return ResponseEntity.noContent().build();
    }
}
