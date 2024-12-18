package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.EventDTO;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.EventRepository;
import com.example.capstone3.Repository.OrganizationRepository;
import com.example.capstone3.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private  final OrganizationRepository organizationRepository;
    private final ArtifactRepository artifactRepository;
    private final RequestRepository requestRepository;

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

    public void addArtifactToEvent (Integer event_id,Integer artifact_id){
    Event event = eventRepository.findEventById(event_id);
        if (event==null){
            throw new ApiException("event not found");
        }
        Artifact artifact =artifactRepository.findArtifactsById(artifact_id);
        if (artifact==null){
            throw new ApiException("artifact not found");
        }

        Request request = requestRepository.findRequestByContributorAndDecision(artifact.getContributor(),"accepted");
        if (request==null){
            throw  new ApiException("Artifact cannot be added to the event without an accepted borrow request");
        }

        event.getArtifacts().add(artifact);
        artifact.setAvailability(false);
        eventRepository.save(event);
        artifactRepository.save(artifact);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public  void updateArtifactAvailability (){
    List<Event> expiredEvent = eventRepository.findByEndDateBefore(LocalDate.now());
    for(Event e: expiredEvent){
        Set<Artifact> artifacts=e.getArtifacts();
        for (Artifact a:artifacts){
            a.setAvailability(true);
            artifactRepository.save(a);
        }
    }
    }

    public  List<EventDTO> convertEventToDTO(Collection<Event> events){
    List<EventDTO> eventDTOS = new ArrayList<>();
    for (Event e : events){
        eventDTOS.add(new EventDTO(e.getName(),e.getDescription(),e.getStartDate(),e.getEndDate(),e.getLocation()));
    }
    return eventDTOS;
    }
}
