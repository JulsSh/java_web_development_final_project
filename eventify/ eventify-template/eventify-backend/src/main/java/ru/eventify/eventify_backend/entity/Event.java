package ru.eventify.eventify_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@ToString
@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 2048)
    private String coverUrl;

    @Column(nullable = false)
    private Instant dateTime;

    @Column(nullable = false)
    private Integer totalTickets;
}
