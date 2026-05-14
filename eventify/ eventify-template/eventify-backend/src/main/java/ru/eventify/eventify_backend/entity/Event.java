package ru.eventify.eventify_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 2048, name="cover_url")
    private String coverUrl;

    @Column(nullable = false, name="date_time")
    private Instant dateTime;

    @Column(nullable = false, name="total_tickets")
    private Integer totalTickets;

    @Column(nullable = false, name="available_tickets")
    private Integer availableTickets;

}
