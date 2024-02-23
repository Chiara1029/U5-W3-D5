package com.chiarapuleio.eventmanagement.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventDTO(
        @NotEmpty(message = "Title required.")
        @Size(min=3, max=30, message = "Title must have from 3 to 30 characters.")
        String title,

        @NotEmpty(message = "Description required.")
        @Size(min=3, max=30, message = "Description must have from 3 to 30 characters.")
        String description,

        @NotEmpty(message = "Date required.")
        LocalDate eventDate,

        @NotEmpty(message = "Location required.")
        @Size(min=3, max=30, message = "Location must have from 3 to 30 characters.")
        String eventLocation,

        @NotNull(message = "Reservation limit required.")
        int maxParticipants
) {
}
