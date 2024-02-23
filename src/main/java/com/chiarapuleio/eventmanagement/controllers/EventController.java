package com.chiarapuleio.eventmanagement.controllers;

import com.chiarapuleio.eventmanagement.entities.Event;
import com.chiarapuleio.eventmanagement.payloads.EventDTO;
import com.chiarapuleio.eventmanagement.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventSrv;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event save(@RequestBody EventDTO event){
        return eventSrv.save(event);
    }

    @GetMapping
    public List<Event> getEvents(){
        return this.eventSrv.getEvents();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event uploadEvent(@PathVariable UUID eventId, @RequestBody Event event){
        return eventSrv.findByIdAndUpdate(eventId, event);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteEvent(@PathVariable UUID eventId){
        eventSrv.findByIdAndDelete(eventId);
    }
}
