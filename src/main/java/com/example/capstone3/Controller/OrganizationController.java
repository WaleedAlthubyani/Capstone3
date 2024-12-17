package com.example.capstone3.Controller;


import com.example.capstone3.Model.Organization;
import com.example.capstone3.Service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;


    @PostMapping("/add")
    public ResponseEntity add (@RequestBody @Valid Organization organization){
           organizationService.add(organization);
           return ResponseEntity.status(200).body("organization added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update (@PathVariable Integer id , @RequestBody @Valid Organization organization){
        organizationService.update(id,organization);
        return ResponseEntity.status(200).body("organization update successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable Integer id){
        organizationService.delete(id);
        return ResponseEntity.status(200).body("organization deleted successfully");
    }
}
