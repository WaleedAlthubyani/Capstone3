package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.EventDTO;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Repository.EventRepository;
import com.example.capstone3.Repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private  final OrganizationRepository organizationRepository;

public List<EventDTO> getAll (){
    return convertEventToDTO(eventRepository.findAll());
}

    public void addEvent ( Integer organization_id ,Event event){
        Organization organization =organizationRepository.findOrganizationById(organization_id);
        if (organization == null) {
            throw new ApiException("Organization not found");
        }
        event.setOrganization(organization);
        eventRepository.save(event);
    }

    public void update (Integer id , Event event){
        Event oldEvent = eventRepository.findEventById(id);
        if (oldEvent==null){
            throw new ApiException("event not found");
        }
        oldEvent.setName(event.getName());
        oldEvent.setDescription(event.getDescription());
        oldEvent.setStartDate(event.getStartDate());
        oldEvent.setEndDate(event.getEndDate());
        oldEvent.setLocation(event.getLocation());

        eventRepository.save(oldEvent);
    }


    public void delete (Integer id){
        Event event =eventRepository.findEventById(id);
        if (event==null){
            throw new ApiException("event not found");
        }
        eventRepository.delete(event);
    }


    public  List<EventDTO> convertEventToDTO(Collection<Event> events){
    List<EventDTO> eventDTOS = new ArrayList<>();
    for (Event e : events){
        eventDTOS.add(new EventDTO(e.getName(),e.getDescription(),e.getStartDate(),e.getEndDate(),e.getLocation()));
    }
    return eventDTOS;
    }
}
