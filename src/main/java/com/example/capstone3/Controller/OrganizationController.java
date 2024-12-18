package com.example.capstone3.Controller;


import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.FeedbackDTO;
import com.example.capstone3.DTO.OrganizationIDTO;
import com.example.capstone3.DTO.ReportIDTO;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Service.OrganizationService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

@GetMapping("/get")
    public ResponseEntity getAll (){
        return ResponseEntity.status(200).body(organizationService.getAllOrganizations());
    }
    @PostMapping("/add")
    public ResponseEntity add (@RequestBody @Valid OrganizationIDTO organizationIDTO){
           organizationService.add(organizationIDTO);
           return ResponseEntity.status(200).body(new ApiResponse("organization added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update (@PathVariable Integer id , @RequestBody @Valid Organization organization){
        organizationService.update(id,organization);
        return ResponseEntity.status(200).body(new ApiResponse("organization update successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable Integer id){
        organizationService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("organization deleted successfully"));
    }

    @PostMapping("/send-feedback/{requestId}")
    public ResponseEntity giveFeedback(@PathVariable Integer requestId,@RequestBody @Valid FeedbackDTO feedbackDTO){
    organizationService.giveFeedback(requestId,feedbackDTO);
    return ResponseEntity.status(200).body(new ApiResponse("feedback sent successfully"));
    }

    @PutMapping("/updateRequest/{organization_id}/{id}")
    public ResponseEntity updateRequest (@PathVariable Integer organization_id ,@PathVariable Integer id, @RequestBody @Valid Request request){
        organizationService.updateRequest(organization_id,id,request);
        return ResponseEntity.status(200).body(new ApiResponse("organization update request successfully"));
    }

    @DeleteMapping("/deleteRequest/{organization_id}/{id}")
    public ResponseEntity deleteRequest ( @PathVariable Integer organization_id, @PathVariable Integer id){
        organizationService.deleteRequest(organization_id,id);
        return ResponseEntity.status(200).body(new ApiResponse("organization deleted request successfully"));
    }

    @PostMapping("/report/{organization_id}")
    public ResponseEntity report (@PathVariable Integer organization_id,@RequestBody @Valid ReportIDTO reportIDTO){
        organizationService.report(organization_id,reportIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("report sent successfully"));
    }

    @GetMapping("/get-reports-sent-by-organization/{organization_id}")
    public ResponseEntity getReportsSentByOrganization (@PathVariable Integer organization_id){
    return ResponseEntity.status(200).body(organizationService.getReportsSentByOrganization(organization_id));
    }

}
