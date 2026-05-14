package ru.eventify.eventify_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    @Column(name="customer_email", nullable = false, length = 254)
    private String customerEmail;

    @Column(name="ticket_count", nullable = false)
    private Integer ticketCount;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @Column(name="expiry_time")
    private Instant expiryTime;

    @Column(nullable = false)
    private boolean confirmed;

    @Column(length = 100)
    private String timezone;




}
