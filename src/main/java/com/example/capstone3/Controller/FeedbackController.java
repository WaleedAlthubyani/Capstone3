package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
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
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(feedbackService.getAll());
    }

    @PutMapping("/update-feedback/{senderEmail}/{feedbackId}")
    public ResponseEntity<?> updateFeedback(@PathVariable String senderEmail, @PathVariable Integer feedbackId, @RequestBody @Valid FeedbackDTO feedbackDTO) {
        feedbackService.updateFeedback(senderEmail, feedbackId, feedbackDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Feedback update successfully"));
    }

    @GetMapping("/feedback-by-organization/{organization_id}")
    public ResponseEntity<?> findFeedbackByOrganization(@PathVariable Integer organization_id) {
        return ResponseEntity.status(200).body(feedbackService.findFeedbackByOrganizationId(organization_id));
    }
}
