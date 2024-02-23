package com.chiarapuleio.eventmanagement.services;

import com.chiarapuleio.eventmanagement.dao.EventDAO;
import com.chiarapuleio.eventmanagement.entities.Event;
import com.chiarapuleio.eventmanagement.exceptions.BadRequestException;
import com.chiarapuleio.eventmanagement.exceptions.NotFoundException;
import com.chiarapuleio.eventmanagement.payloads.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventDAO eventDAO;

    public Event save(EventDTO event){
        Event newEvent = new Event();
        newEvent.setTitle(event.title());
        newEvent.setDescription(event.description());
        newEvent.setEventDate(event.eventDate());
        newEvent.setMaxParticipants(event.maxParticipants());

        return eventDAO.save(newEvent);
    }
    public List<Event> getEvents(){
        return this.eventDAO.findAll();
    }

    public Event findById(UUID eventId){
        return eventDAO.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
    }

    public Event findByIdAndUpdate(UUID eventId, Event event){
        Event eventFound = this.findById(eventId);
        eventFound.setTitle(event.getTitle());
        eventFound.setDescription(event.getDescription());
        eventFound.setEventDate(event.getEventDate());
        eventFound.setEventLocation(event.getEventLocation());
        eventFound.setMaxParticipants(event.getMaxParticipants());

        return eventDAO.save(eventFound);
    }

    public void findByIdAndDelete(UUID eventId){
        Event event = this.findById(eventId);
        if(!event.getReservations().isEmpty()){
            throw new BadRequestException(eventId + " cannot be deleted.");
        }
        eventDAO.delete(event);
    }
}
