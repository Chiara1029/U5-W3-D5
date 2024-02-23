package com.chiarapuleio.eventmanagement.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReservationDTO(
        @NotNull(message = "Event ID required.")
        UUID eventId,

        @NotNull(message = "User ID required.")
        UUID userId,

        @NotNull(message = "Tickets for this event are required.")
        int reservedTicket
) {
}
