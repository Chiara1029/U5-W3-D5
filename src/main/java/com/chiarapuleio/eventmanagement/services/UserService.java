package com.chiarapuleio.eventmanagement.services;

import com.chiarapuleio.eventmanagement.dao.UserDAO;
import com.chiarapuleio.eventmanagement.entities.User;
import com.chiarapuleio.eventmanagement.exceptions.NotFoundException;
import com.chiarapuleio.eventmanagement.payloads.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> getUsers(){
        return this.userDAO.findAll();
    }

    public User findById(UUID userId){
        return userDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException(email + " not found."));
    }
}
