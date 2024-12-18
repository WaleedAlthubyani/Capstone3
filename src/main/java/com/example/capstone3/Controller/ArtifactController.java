package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.ArtifactIDTO;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Service.ArtifactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artifact")
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtifactService artifactService;

    @GetMapping("/get")
    public ResponseEntity getArtifacts() {
        return ResponseEntity.status(200).body(artifactService.getArtifacts());
    }

    @PutMapping("/update/{artifactId}")
    public ResponseEntity updateArtifact(@PathVariable Integer artifactId, @RequestBody @Valid ArtifactIDTO artifactIDTO) {
        artifactService.updateArtifact(artifactId, artifactIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated artifact"));
    }

    @DeleteMapping("/delete/{artifactId}/{contributorID}")
    public ResponseEntity deleteArtifact(@PathVariable Integer artifactId, @PathVariable Integer contributorID) {
        artifactService.deleteArtifact(artifactId, contributorID);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted artifact"));
    }

    @PutMapping("/updateArtifactAvailability/{artifact_id}/{contributor_id}/{availability}")
    public ResponseEntity updateArtifactAvailability (@PathVariable Integer artifact_id ,@PathVariable Integer contributor_id,@PathVariable Boolean availability){
        artifactService.updateArtifactAvailability(artifact_id,contributor_id,availability);
        return ResponseEntity.status(200).body(new ApiResponse("update artifact availability"));
    }

}
