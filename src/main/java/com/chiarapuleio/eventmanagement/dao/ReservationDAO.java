package com.chiarapuleio.eventmanagement.dao;

import com.chiarapuleio.eventmanagement.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationDAO extends JpaRepository<Reservation, UUID> {
}
