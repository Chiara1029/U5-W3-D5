package com.chiarapuleio.eventmanagement.services;

import com.chiarapuleio.eventmanagement.dao.ReservationDAO;
import com.chiarapuleio.eventmanagement.entities.Event;
import com.chiarapuleio.eventmanagement.entities.Reservation;
import com.chiarapuleio.eventmanagement.entities.User;
import com.chiarapuleio.eventmanagement.exceptions.NotFoundException;
import com.chiarapuleio.eventmanagement.payloads.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private UserService userSrv;
    @Autowired
    private EventService eventSrv;

    public Reservation save(ReservationDTO reservation){
        Event event = eventSrv.findById(reservation.eventId());
        User user = userSrv.findById(reservation.userId());
        Reservation res = new Reservation();
        res.setReservedTickets(reservation.reservedTicket());
        res.setUser(user);
        res.setEvent(event);

        return reservationDAO.save(res);
    }

    public Reservation findById(UUID resId){
        return reservationDAO.findById(resId).orElseThrow(() -> new NotFoundException(resId));
    }
}
