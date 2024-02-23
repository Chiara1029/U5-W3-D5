package com.chiarapuleio.eventmanagement.controllers;

import com.chiarapuleio.eventmanagement.entities.Reservation;
import com.chiarapuleio.eventmanagement.payloads.ReservationDTO;
import com.chiarapuleio.eventmanagement.payloads.ReservationRespDTO;
import com.chiarapuleio.eventmanagement.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService resSrv;

    @PostMapping
    public ReservationRespDTO save(@RequestBody ReservationDTO reservation){
        Reservation res = resSrv.save(reservation);
        return new ReservationRespDTO(res.getId(), res.getEvent().getEventDate());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
    public List<Reservation> getReservations(){
        return this.resSrv.getReservations();
    }
}
