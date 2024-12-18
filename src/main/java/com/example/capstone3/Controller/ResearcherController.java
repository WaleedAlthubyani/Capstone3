package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.ResearcherIDTO;
import com.example.capstone3.Service.ResearcherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/researcher")
@RequiredArgsConstructor
public class ResearcherController {

    private final ResearcherService researcherService;

    @GetMapping("/get")
    public ResponseEntity getAllResearchers(){
        return ResponseEntity.status(200).body(researcherService.getAllResearchers());
    }

    @PostMapping("/add")
    public ResponseEntity addResearcher(@RequestBody @Valid ResearcherIDTO researcherIDTO){
        researcherService.addResearcher(researcherIDTO);
        return ResponseEntity.status(201).body(new ApiResponse("Researcher added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateResearcher(@PathVariable Integer id,@RequestBody @Valid ResearcherIDTO researcherIDTO){
        researcherService.updateResearcher(id,researcherIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Researcher updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteResearcher(@PathVariable Integer id){
        researcherService.deleteResearcher(id);
        return ResponseEntity.status(200).body(new ApiResponse("Researcher deleted successfully"));
    }
}
