package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.FeedbackDTO;
import com.example.capstone3.Service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/get")
    public ResponseEntity getAll (){
        return ResponseEntity.status(200).body(feedbackService.getAll());
    }

    @PostMapping("/createFeedback/{requestId}")
    public ResponseEntity createFeedback (@PathVariable Integer requestId,@RequestBody @Valid FeedbackDTO feedbackDTO){
        feedbackService.createFeedback(requestId,feedbackDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Feedback added successfully"));
    }

    @PutMapping("/updateFeedback/{id}")
    public ResponseEntity updateFeedback (@PathVariable Integer id, @RequestBody @Valid FeedbackDTO feedbackDTO){
        feedbackService.updateFeedback(id,feedbackDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Feedback update successfully"));
    }

    @GetMapping("/feedbackByOrganization/{organization_id}")
    public ResponseEntity findFeedbackByOrganization (@PathVariable Integer organization_id){
        return ResponseEntity.status(200).body(feedbackService.findFeedbackByOrganizationId(organization_id));
    }
}
