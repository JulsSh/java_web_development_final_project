package ru.eventify.eventify_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="notification_preferences")
public class NotificationPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name="notify_new_events")
    private boolean notifyNewEvents;

    @Column(name="notify_upcoming")
    private boolean notifyUpcoming;

    @Column(name="notify_before_hours")
    private Integer notifyBeforeHours;

}
