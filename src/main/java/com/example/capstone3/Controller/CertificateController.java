package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Certificate;
import com.example.capstone3.Service.CertificateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/certificate")
@RequiredArgsConstructor
public class CertificateController {
    private final CertificateService certificateService;


    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(certificateService.getAll());
    }

    @PostMapping("/addCertificate/{artifact_id}")
    public ResponseEntity<?> addCertificate(@PathVariable Integer artifact_id, @RequestBody @Valid Certificate certificate) {
        certificateService.addCertificate(artifact_id,certificate);
        return ResponseEntity.status(200).body(new ApiResponse("Certificate added successfully"));
    }

    @PutMapping("/updateCertificate/{id}")
    public ResponseEntity<?> updateCertificate(@PathVariable Integer id, @RequestBody @Valid Certificate certificate) {
        certificateService.updateCertificate(id,certificate);
        return ResponseEntity.status(200).body(new ApiResponse("Certificate update successfully"));
    }
}
