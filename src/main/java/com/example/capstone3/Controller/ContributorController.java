package com.example.capstone3.Controller;

import com.example.capstone3.DTO.ContributorIDTO;
import com.example.capstone3.Service.ContributorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
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
        return ResponseEntity.status(201).body("Contributor added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateContributor(@PathVariable Integer id,@RequestBody @Valid ContributorIDTO contributorIDTO){
        contributorService.updateContributor(id,contributorIDTO);
        return ResponseEntity.status(200).body("Contributor updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteContributor(@PathVariable Integer id){
        contributorService.deleteContributor(id);
        return ResponseEntity.status(200).body("Contributor deleted successfully");
    }
}
