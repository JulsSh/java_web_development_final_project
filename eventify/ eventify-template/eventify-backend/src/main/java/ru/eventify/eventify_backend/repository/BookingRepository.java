package ru.eventify.eventify_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eventify.eventify_backend.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
