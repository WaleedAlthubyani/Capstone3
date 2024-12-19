package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
//Waleed
    private final AdminService adminService;

    //waleed
    @GetMapping("/get-pending-researchers/{id}")
    public ResponseEntity<?> getPendingResearchers(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adminService.getPendingResearchers(id));
    }

    //waleed
    @PutMapping("/decide-on-researcher/{admin-id}/{researcher-id}/{decision}")
    public ResponseEntity<?> decideOnResearcher(@PathVariable(name = "admin-id") Integer adminId, @PathVariable(name = "researcher-id") Integer researcherId, @PathVariable String decision) {
        adminService.decideOnResearcher(adminId,researcherId,decision);
        return ResponseEntity.status(200).body(decision+" successfully");
    }

    //waleed
    @GetMapping("/get-pending-organizations/{id}")
    public ResponseEntity<?> getPendingOrganizations(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adminService.getPendingOrganizations(id));
    }

    //waleed
    @PutMapping("/decide-on-organization/{admin-id}/{organization-id}/{decision}")
    public ResponseEntity<?> decideOnOrganization(@PathVariable(name = "admin-id") Integer adminId, @PathVariable(name = "organization-id") Integer organizationId, @PathVariable String decision) {
        adminService.decideOnOrganization(adminId, organizationId, decision);
        return ResponseEntity.status(200).body(decision + " successfully");
    }

    //waleed
    @GetMapping("/get-pending-artifacts/{id}")
    public ResponseEntity<?> getPendingArtifacts(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adminService.getPendingArtifacts(id));
    }

    //waleed
    @PutMapping("/decide-on-artifact/{admin-id}/{artifact-id}/{decision}")
    public ResponseEntity<?> decideOnArtifact(@PathVariable(name = "admin-id") Integer adminId, @PathVariable(name = "artifact-id") Integer artifactId, @PathVariable String decision) {
        adminService.decideOnArtifact(adminId, artifactId, decision);
        return ResponseEntity.status(200).body(decision + " successfully");
    }

    //bayan
    @GetMapping("/get-banlist")
    public ResponseEntity<?> getBanList() {
        return ResponseEntity.status(200).body(adminService.getBanList());
    }

    @GetMapping("/get-reports/{id}")
    public ResponseEntity<?> getAllReports(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adminService .getAllReports(id));
    }

    @PutMapping("/ban-contributor/{admin-id}/{contributor-id}/{reason}")
    public ResponseEntity<?> banContributor(@PathVariable(name = "admin-id") Integer adminId, @PathVariable(name = "contributor-id") Integer contributorId, @PathVariable String reason) {
        adminService.banContributor(adminId,contributorId,reason);
        return ResponseEntity.status(200).body(new ApiResponse("Contributor banned successfully"));
    }
    @PutMapping("/ban-researcher/{admin-id}/{researcher-id}/{reason}")
    public ResponseEntity<?> banResearcher(@PathVariable(name = "admin-id") Integer adminId, @PathVariable(name = "researcher-id") Integer researcherId, @PathVariable String reason){
        adminService.banResearcher(adminId,researcherId,reason);
        return ResponseEntity.status(200).body(new ApiResponse("Researcher banned successfully"));
    }

    @PutMapping("/ban-organization/{admin-id}/{organization-id}/{reason}")
    public ResponseEntity<?> banOrganization(@PathVariable(name = "admin-id") Integer adminId, @PathVariable(name = "organization-id") Integer organizationId, @PathVariable String reason) {
        adminService.banOrganization(adminId,organizationId,reason);
        return ResponseEntity.status(200).body(new ApiResponse("Organization banned successfully"));
    }
}
