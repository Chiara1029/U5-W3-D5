package com.chiarapuleio.eventmanagement.dao;

import com.chiarapuleio.eventmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDAO extends JpaRepository<User, UUID> {
}