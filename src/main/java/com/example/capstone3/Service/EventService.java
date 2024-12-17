package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.ApiRespose.ApiException;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public void add (Event event){
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
}
