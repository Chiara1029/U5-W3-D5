package com.chiarapuleio.eventmanagement.payloads;

import java.time.LocalDate;
import java.util.UUID;

public record ReservationRespDTO(UUID resId, LocalDate eventDate) {
}
