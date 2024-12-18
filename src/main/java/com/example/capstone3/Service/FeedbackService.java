package com.example.capstone3.Service;

import com.example.capstone3.Model.Feedback;
import com.example.capstone3.Repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.FeedbackDTO;
import com.example.capstone3.DTO.FeedbackODTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final RequestRepository requestRepository;
    private final OrganizationRepository organizationRepository;
    private final ContributorRepository contributorRepository;
    private final ResearcherRepository researcherRepository;



    public List<FeedbackODTO> getAll()  {
        return convertFeedBackToDTo(feedbackRepository.findAll());
    }

    public void createFeedback(Integer requestId,FeedbackDTO feedbackDTO) {
        Request request = requestRepository.findRequestById(requestId);
        if (request == null) {
            throw new ApiException("Request not found");
        }
        if (!request.getDecision().equalsIgnoreCase("accepted") &&
                !request.getDecision().equalsIgnoreCase("rejected")) {
            throw new ApiException("Feedback can only be created for completed requests.");
        }
        Feedback feedback = new Feedback();
        feedback.setComment(feedbackDTO.getComment());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setCreatedAt(LocalDate.now());
        feedback.setSenderId(feedbackDTO.getSenderId());
        feedback.setReceiverId(feedbackDTO.getReceiverId());

        switch (feedbackDTO.getCreatorType().toLowerCase()) {
            case "organization":
                Organization organization = organizationRepository.findOrganizationById(feedbackDTO.getCreatorId());
                if (organization == null) {
                    throw new ApiException("Organization not found");
                }
                feedback.setOrganization(organization);
                break;
            case "contributor":
                Contributor contributor = contributorRepository.findContributorById(feedbackDTO.getCreatorId());
                if (contributor == null) {
                    throw new ApiException("contributor not found");
                }
                feedback.setContributor(contributor);
                break;
            case "researcher":
                Researcher researcher = researcherRepository.findResearcherById(feedbackDTO.getCreatorId());
                if (researcher == null) {
                    throw new ApiException("researcher not found");
                }
                feedback.setResearcher(researcher);
                break;
            default:
                throw new ApiException("Invalid creator type: " + feedbackDTO.getCreatorType());
        }
         feedbackRepository.save(feedback);
    }

    public void updateFeedback (Integer id,FeedbackDTO feedbackDTO ){
        Feedback feedback = feedbackRepository.findFeedbackById(id);
        if (feedback==null){
            throw new ApiException("feedback not found");
        }
        if (ChronoUnit.DAYS.between(feedback.getCreatedAt(),LocalDate.now())>3){
            throw new ApiException("feedback cannot be updated after 3 days");
        }
        if (feedbackDTO.getCreatorType().equalsIgnoreCase("organization") &&
        feedback.getOrganization()==null || !feedback.getOrganization().getId().equals(feedbackDTO.getCreatorId())){
            throw new ApiException("you not authorized to update this feedback");
        }
        if (feedbackDTO.getCreatorType().equalsIgnoreCase("contributor") &&
                feedback.getContributor()==null || !feedback.getContributor().getId().equals(feedbackDTO.getCreatorId())){
            throw new ApiException("you not authorized to update this feedback");
        }
        if (feedbackDTO.getCreatorType().equalsIgnoreCase("researcher") &&
                feedback.getResearcher()==null || !feedback.getResearcher().getId().equals(feedbackDTO.getCreatorId())){
            throw new ApiException("you not authorized to update this feedback");
        }
        feedback.setComment(feedbackDTO.getComment());
        feedback.setRating(feedbackDTO.getRating());
        feedbackRepository.save(feedback);
    }

    public List<FeedbackODTO> findFeedbackByOrganizationId (Integer organization_id){
        List<Feedback> feedbackByOrganizationId= feedbackRepository.findFeedbackByOrganizationId(organization_id);
        return convertFeedBackToDTo(feedbackByOrganizationId);
    }

    public List<FeedbackODTO> convertFeedBackToDTo (Collection<Feedback> feedbacks){
        List<FeedbackODTO> feedbackODTOS= new ArrayList<>();
        for (Feedback f : feedbacks) {
            String creatorType = "";
            String creatorName = "";
            if (f.getOrganization() != null) {
                creatorType = "Organization";
                creatorName = f.getOrganization().getName();
            } else if (f.getContributor() != null) {
                creatorType = "Contributor";
                creatorName = f.getContributor().getName();
            } else if (f.getResearcher() != null) {
                creatorType = "Researcher";
                creatorName = f.getResearcher().getName();
            }
            feedbackODTOS.add(new FeedbackODTO(
                    f.getComment(),
                    f.getRating(),
                    creatorType,
                    creatorName
            ));
        }

        return feedbackODTOS;
    }

    }

//    private final FeedbackRepository feedbackRepository;

//    public List<FeedBackODTO> getResearcherFeedbacks(Researcher researcher) {
//        return convertResearcherFeedbacksToDTO(feedbackRepository.findFeedbacksByResearcher(researcher));
//    }

//    public void researcherGiveFeedback(Feedback feedback) {
//        feedbackRepository.save(feedback);
//    }

//    public List<FeedBackODTO> convertResearcherFeedbacksToDTO(List<Feedback> feedback) {
//        List<FeedBackODTO> feedBackDTOs = new ArrayList<>();
//        for (Feedback feedback1 : feedback) {
//            feedBackDTOs.add(new FeedBackODTO(feedback1.getContributor(),feedback1.getComment(),feedback1.getRating()));
//        }
//
//        return feedBackDTOs;
//    }

