package com.chiarapuleio.eventmanagement.dao;

import com.chiarapuleio.eventmanagement.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventDAO extends JpaRepository<Event, UUID> {
}
