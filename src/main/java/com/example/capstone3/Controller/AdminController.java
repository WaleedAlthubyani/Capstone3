package com.example.capstone3.Controller;

import com.example.capstone3.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get-pending-researchers/{id}")
    public ResponseEntity getPendingResearchers(@PathVariable Integer id){
        return ResponseEntity.status(200).body(adminService.getPendingResearchers(id));
    }

    @PutMapping("/decide-on-researcher/{admin-id}/{researcher-id}/{decision}")
    public ResponseEntity decideOnResearcher(@PathVariable(name = "admin-id") Integer adminId,@PathVariable(name = "researcher-id") Integer researcherId,@PathVariable  String decision){
        adminService.decideOnResearcher(adminId,researcherId,decision);
        return ResponseEntity.status(200).body(decision+" successfully");
    }

    @GetMapping("/get-pending-organizations/{id}")
    public ResponseEntity getPendingOrganizations(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adminService.getPendingOrganizations(id));
    }

    @PutMapping("/decide-on-organization/{admin-id}/{organization-id}/{decision}")
    public ResponseEntity decideOnOrganization(@PathVariable(name = "admin-id") Integer adminId, @PathVariable(name = "organization-id") Integer organizationId, @PathVariable String decision) {
        adminService.decideOnOrganization(adminId, organizationId, decision);
        return ResponseEntity.status(200).body(decision + " successfully");
    }

    @GetMapping("/get-pending-artifacts/{id}")
    public ResponseEntity getPendingArtifacts(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adminService.getPendingArtifacts(id));
    }

    @PutMapping("/decide-on-artifact/{admin-id}/{artifact-id}/{decision}")
    public ResponseEntity decideOnArtifact(@PathVariable(name = "admin-id") Integer adminId, @PathVariable(name = "artifact-id") Integer artifactId, @PathVariable String decision) {
        adminService.decideOnArtifact(adminId, artifactId, decision);
        return ResponseEntity.status(200).body(decision + " successfully");
    }
    @GetMapping("/get-banlist")
    public ResponseEntity getBanList (){
        return ResponseEntity.status(200).body(adminService.getBanList());
    }
}
