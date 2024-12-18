package com.example.capstone3.Service;

import com.example.capstone3.Model.Feedback;
import com.example.capstone3.Repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

//    public List<FeedBackODTO> getResearcherFeedbacks(Researcher researcher) {
//        return convertResearcherFeedbacksToDTO(feedbackRepository.findFeedbacksByResearcher(researcher));
//    }

    public void researcherGiveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

//    public List<FeedBackODTO> convertResearcherFeedbacksToDTO(List<Feedback> feedback) {
//        List<FeedBackODTO> feedBackDTOs = new ArrayList<>();
//        for (Feedback feedback1 : feedback) {
//            feedBackDTOs.add(new FeedBackODTO(feedback1.getContributor(),feedback1.getComment(),feedback1.getRating()));
//        }
//
//        return feedBackDTOs;
//    }
}
