package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.RequestIDTO;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;


    @PostMapping("/requestBorrowArtifact/{organization_id}/{artifact_id}")
    public ResponseEntity requestBorrowArtifact (@PathVariable Integer organization_id, @PathVariable Integer artifact_id , @RequestBody @Valid RequestIDTO request){
        requestService.requestBorrowArtifact(organization_id,artifact_id,request);
        return ResponseEntity.status(200).body(new ApiResponse("request added successfully"));
    }
}
