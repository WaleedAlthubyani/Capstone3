package com.example.capstone3.Controller;

import com.example.capstone3.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
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
}
