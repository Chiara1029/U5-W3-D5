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

    public User save(User user){
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        return userDAO.save(newUser);
    }

    public List<User> getUsers(){
        return this.userDAO.findAll();
    }

    public User findById(UUID userId){
        return userDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }


}
