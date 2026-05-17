package ru.eventify.eventify_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eventify.eventify_backend.entity.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
List<Booking> findByCustomerEmail(String email);
}
