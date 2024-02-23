package com.chiarapuleio.eventmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private String eventLocation;
    private int maxParticipants;
    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Reservation> reservations;

    public Event(String title, String description, LocalDate eventDate, String eventLocation, int maxParticipants) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.maxParticipants = maxParticipants;
        this.reservations = new ArrayList<>();
    }
}
