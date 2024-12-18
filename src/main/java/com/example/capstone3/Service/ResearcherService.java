package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.*;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.FeedbackRepository;
import com.example.capstone3.Repository.RequestRepository;
import com.example.capstone3.Repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ResearcherService {

    private final ResearcherRepository researcherRepository;
    private final ArtifactRepository artifactRepository;
    private final RequestService requestService;
    private final FeedbackService feedbackService;
    private final FeedbackRepository feedbackRepository;
    private final RequestRepository requestRepository;

    public List<ResearcherODTO> getAllResearchers(){
        return convertResearcherToDTO(researcherRepository.findAllApprovedResearcher());
    }

    public void addResearcher(ResearcherIDTO researcherIDTO){
        if (researcherRepository.existsByEmail(researcherIDTO.getEmail()))
            throw new ApiException("Email already exists");
        if (researcherRepository.existsByPhoneNumber(researcherIDTO.getPhoneNumber()))
            throw new ApiException("Phone number already exists");
        if (researcherRepository.existsByLicenseURL(researcherIDTO.getLicenseURL()))
            throw new ApiException("License already in use");

        Researcher researcher=new Researcher();
        researcher.setName(researcherIDTO.getName());
        researcher.setEmail(researcherIDTO.getEmail());
        researcher.setPassword(researcherIDTO.getPassword());
        researcher.setLicenseURL(researcher.getLicenseURL());
        researcher.setCreatedAt(LocalDateTime.now());

        researcherRepository.save(researcher);
    }

    public void updateResearcher(Integer id,ResearcherIDTO researcherIDTO){
        Researcher researcher=researcherRepository.findResearcherById(id);

        if (researcher==null) throw new ApiException("Researcher not found");

        if (!researcher.getEmail().equalsIgnoreCase(researcherIDTO.getEmail())){
            if (researcherRepository.existsByEmail(researcherIDTO.getEmail()))
                throw new ApiException("Email already exists");
        }

        if (!researcher.getPhoneNumber().equals(researcherIDTO.getPhoneNumber())){
            if (researcherRepository.existsByPhoneNumber(researcherIDTO.getPhoneNumber()))
                throw new ApiException("Phone number already exists");
        }

        if (!researcher.getLicenseURL().equals(researcherIDTO.getLicenseURL())){
            researcher.setStatus("pending");
            if (researcherRepository.existsByLicenseURL(researcherIDTO.getLicenseURL())){
                throw new ApiException("License already exists");
            }
        }

        researcher.setName(researcherIDTO.getName());
        researcher.setEmail(researcherIDTO.getEmail());
        researcher.setPassword(researcherIDTO.getPassword());
        researcher.setPhoneNumber(researcherIDTO.getPhoneNumber());
        researcher.setLicenseURL(researcherIDTO.getLicenseURL());
        researcher.setFieldOfStudy(researcherIDTO.getFieldOfStudy());

        researcherRepository.save(researcher);
    }

    public void deleteResearcher(Integer id){
        Researcher researcher=researcherRepository.findResearcherById(id);

        if (researcher==null) throw new ApiException("Researcher not found");

        researcherRepository.delete(researcher);
    }

    public void requestToBorrowArtifact(Integer researcherId, Integer artifactId, RequestIDTO requestIDTO){
        Researcher researcher=researcherRepository.findResearcherById(researcherId);
        if (researcher==null) throw new ApiException("Researcher not found");

        Artifact artifact=artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");

        Request request=new Request();
        request.setResearcher(researcher);
        request.setContributor(artifact.getContributor());
        request.setStartDate(requestIDTO.getStartDate());
        request.setEndDate(requestIDTO.getEndDate());
        request.setType("research");
        request.setCreatedAt(LocalDateTime.now());
        request.setDecision("pending");

        requestService.requestBorrowArtifact(null,artifactId,request);
    }

    public void giveFeedbackOnArtifactOwner(Integer researcherId,Integer requestId,FeedbackDTO feedbackDTO){
        Researcher researcher=researcherRepository.findResearcherById(researcherId);
        if (researcher==null) throw new ApiException("Researcher not found");
        Request request=requestRepository.findRequestById(requestId);
        if (request==null) throw new ApiException("Request not found");

        if (!request.getResearcher().equals(researcher)){
            throw new ApiException("Researcher did not create this request");
        }
        if (!request.getDecision().equals("accepted")){
            throw new ApiException("Request not accepted");
        }
        if (request.getEndDate().isAfter(LocalDate.now())){
            throw new ApiException("can't give feedback until end date");
        }

        feedbackDTO.setCreatorId(researcher.getId());
        feedbackDTO.setCreatorType("research");
        feedbackDTO.setSenderId(researcher.getId());
        feedbackDTO.setReceiverId(request.getContributor().getId());

        feedbackService.createFeedback(requestId,feedbackDTO);

    }

    public List<FeedbackODTO> getFeedbacks (Integer researcherId){
        Researcher researcher=researcherRepository.findResearcherById(researcherId);
        if (researcher==null) throw new ApiException("Researcher not found");
        List<Feedback>feedbacks= feedbackRepository.findFeedbackByReceiverId(researcher.getId());
        return feedbackService.convertFeedBackToODTo(feedbacks);
    }

    public List<ResearcherODTO> convertResearcherToDTO(Collection<Researcher> researchers){
        List<ResearcherODTO> researcherODTOS=new ArrayList<>();

        for (Researcher r:researchers){
            researcherODTOS.add(new ResearcherODTO(r.getName(),r.getEmail(),r.getPhoneNumber(),r.getFieldOfStudy(),r.getLicenseURL()));
        }

        return researcherODTOS;
    }



    public List<Artifact> getRecommendation (Integer researcher_id){
        List<Request> previousRequest = requestRepository.findByResearcherId(researcher_id);
        if (previousRequest==null){
            throw new ApiException("you dont have previous");
        }
        Set<String> artifactType=new HashSet<>();
        for(Request r :previousRequest){
            for (Artifact a:r.getContributor().getArtifacts()){
                artifactType.add(a.getType());
            }
        } List<Artifact> recommendation =new ArrayList<>();
        for (String s :artifactType){
            recommendation.addAll(artifactRepository.findArtifactByType(s));
        }return recommendation;
    }
}
