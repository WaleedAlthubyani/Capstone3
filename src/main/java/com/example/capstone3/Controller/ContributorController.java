package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.*;
import com.example.capstone3.Service.ContributorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contributor")
@RequiredArgsConstructor
public class ContributorController {

    private final ContributorService contributorService;

    @GetMapping("/get")
    public ResponseEntity getAllContributors(){
        return ResponseEntity.status(200).body(contributorService.getAllContributors());
    }

    @PostMapping("/add")
    public ResponseEntity addContributor(@RequestBody @Valid ContributorIDTO contributorIDTO){
        contributorService.addContributor(contributorIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Contributor added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateContributor(@PathVariable Integer id,@RequestBody @Valid ContributorIDTO contributorIDTO){
        contributorService.updateContributor(id,contributorIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Contributor updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteContributor(@PathVariable Integer id){
        contributorService.deleteContributor(id);
        return ResponseEntity.status(200).body(new ApiResponse("Contributor deleted successfully"));
    }


    //Mshari
    @PostMapping("/add-artifact")
    public ResponseEntity addArtifact(@RequestBody @Valid ArtifactIDTO artifactIDTO) {
        contributorService.addArtifact(artifactIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added artifact"));
    }

    @PutMapping("/assign-tag/{artifactId}/{tagId}")
    public ResponseEntity assignTag(@PathVariable Integer artifactId,@PathVariable Integer tagId){
        contributorService.assignTag(artifactId,tagId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully assigned tag"));
    }

    @PutMapping("/assign-category/{artifactId}/{categoryId}")
    public ResponseEntity assignCategory(@PathVariable Integer artifactId, @PathVariable Integer categoryId){
        contributorService.assignCategory(artifactId,categoryId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully assigned category"));
    }

    @PostMapping("/add-image/{artifactId}/{url}")
    public ResponseEntity addImage(@PathVariable Integer artifactId, @PathVariable String url, @RequestBody String description){
        contributorService.addImage(artifactId,url,description);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added image"));
    }

    @PostMapping("/add-ownership-history/{artifactId}")
    public ResponseEntity addOwnershipHistory(@PathVariable Integer artifactId,@RequestBody @Valid OwnershipHistoryIDTO ownershipHistoryIDTO){
        contributorService.addOwnershipHistory(artifactId,ownershipHistoryIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added owner history"));
    }

    @PostMapping("/add-certificate/{artifactId}")
    public ResponseEntity addCertificate(@PathVariable Integer artifactId, @RequestBody @Valid CertificateIDTO certificateIDTO){
        contributorService.addCertificate(artifactId,certificateIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added certificate"));
    }

    @PostMapping("/request-artifact-certification/{artifactId}")
    public ResponseEntity requestArtifactCertification(@PathVariable Integer artifactId){
        contributorService.requestArtifactCertification(artifactId);
        return ResponseEntity.status(200).body("redirect:https://abdea.moc.gov.sa/");
    }

    @GetMapping("/view-borrowing-requests/{contributorId}")
    public ResponseEntity viewBorrowingRequests(@PathVariable Integer contributorId){
       return ResponseEntity.status(200).body(contributorService.viewBorrowingRequests(contributorId));
    }

    @PutMapping("/decide-on-borrow/{requestId}/{decision}")
    public ResponseEntity decideOnBorrowRequest(@PathVariable Integer requestId, @PathVariable String decision){
        contributorService.decideOnBorrowRequest(requestId,decision);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully decided on borrow request"));
    }

    @PostMapping("/send-feedback/{requestId}")
    public ResponseEntity giveFeedbackOnBorrower(@PathVariable Integer requestId,@RequestBody @Valid FeedbackDTO feedbackDTO){
        contributorService.giveFeedbackOnBorrower(requestId,feedbackDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Feedback sent successfully"));
    }

    @GetMapping("/view-received-feedbacks/{id}")
    public ResponseEntity viewReceivedFeedbacks(@PathVariable Integer id){
        return ResponseEntity.status(200).body(contributorService.viewReceivedFeedbacks(id));
    }


}
