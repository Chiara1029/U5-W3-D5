package com.chiarapuleio.eventmanagement.controllers;

import com.chiarapuleio.eventmanagement.entities.Reservation;
import com.chiarapuleio.eventmanagement.entities.User;
import com.chiarapuleio.eventmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userSrv;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getUsers(){
        return this.userSrv.getUsers();
    }

    @GetMapping("/me")
    public User getUser(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/me/reservations")
    public List<Reservation> getUserReservations(@AuthenticationPrincipal User user){
        return user.getReservationList();
    }

    @PatchMapping("/me/setmanager")
    public User findByTokenAndChangeRole(@AuthenticationPrincipal User user) {
        User updateUser = userSrv.findByTokenAndChangeRole(user);
        return updateUser;
    }
}
