package com.chiarapuleio.eventmanagement.services;

import com.chiarapuleio.eventmanagement.dao.UserDAO;
import com.chiarapuleio.eventmanagement.entities.User;
import com.chiarapuleio.eventmanagement.enums.Role;
import com.chiarapuleio.eventmanagement.exceptions.BadRequestException;
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

    // so che non è la forma corretta, ma mi serviva un metodo per avere almeno un ADMIN per creare gli eventi
    public User findByTokenAndChangeRole(User user) {
        if (user.getRole().name().equals("USER")) {
            user.setRole(Role.ADMIN);
        } else if (user.getRole().name().equals("ADMIN")) {
            user.setRole(Role.USER);
        } else {
            throw new BadRequestException("You can choose USER or ADMIN");
        }
        return userDAO.save(user);
    }
}
