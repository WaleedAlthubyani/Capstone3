package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.*;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.ContributorRepository;
import com.example.capstone3.Repository.RequestRepository;
import com.example.capstone3.Repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResearcherService {

    private final ResearcherRepository researcherRepository;
    private final ArtifactRepository artifactRepository;
    private final RequestService requestService;
    private final FeedbackService feedbackService;
    private final ContributorRepository contributorRepository;
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

//    public void giveFeedbackOnArtifactOwner(Integer researcherId, Integer contributorId, FeedbackDTO feedbackDTO){
//        Researcher researcher=researcherRepository.findResearcherById(researcherId);
//        if (researcher==null) throw new ApiException("Researcher not found");
//        Contributor contributor=contributorRepository.findContributorById(contributorId);
//        if (contributor==null) throw new ApiException("Contributor not found");
//
//        List<Request> requests=requestRepository.findRequestsByContributorAndResearcherAndDecision(contributor,researcher,"approved");
//        if (requests.isEmpty()) throw new ApiException("You didn't borrow any artifacts from this contributor");
//
//        if (requests.getFirst().getEndDate().isAfter(LocalDateTime.now())){
//            throw new ApiException("Borrow period must end before giving feedback");
//        }else{
//            Feedback feedback=new Feedback();
//            feedback.setContributor(contributor);
//            feedback.setComment(feedbackDTO.getComment());
//            feedback.setRating(feedbackDTO.getRating());
//            feedback.setResearcher(researcher);
//            feedback.setCreatedAt(LocalDate.now());
//            feedbackService.researcherGiveFeedback(feedback);
//        }
//    }

//    public List<FeedBackODTO> getFeedbacks(Integer researcherId){
//        Researcher researcher=researcherRepository.findResearcherById(researcherId);
//        if (researcher==null) throw new ApiException("Researcher not found");
//
//        return feedbackService.getResearcherFeedbacks(researcher);
//    }

    public List<ResearcherODTO> convertResearcherToDTO(Collection<Researcher> researchers){
        List<ResearcherODTO> researcherODTOS=new ArrayList<>();

        for (Researcher r:researchers){
            researcherODTOS.add(new ResearcherODTO(r.getName(),r.getEmail(),r.getPhoneNumber(),r.getFieldOfStudy(),r.getLicenseURL()));
        }

        return researcherODTOS;
    }
}
