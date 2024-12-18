package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;


    @PutMapping("/requestBorrowArtifact/{organization_id}/{artifact_id}")
    public ResponseEntity requestBorrowArtifact (@PathVariable Integer organization_id, @PathVariable Integer artifact_id , @RequestBody @Valid Request request){
        requestService.requestBorrowArtifact(organization_id,artifact_id,request);
        return ResponseEntity.status(200).body(new ApiResponse("request added successfully"));
    }
}
