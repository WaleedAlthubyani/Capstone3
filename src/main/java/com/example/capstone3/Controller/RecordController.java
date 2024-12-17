package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.RecordIDTO;
import com.example.capstone3.Service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/record")
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/add")
    public ResponseEntity addRecord(@RequestBody @Valid RecordIDTO recordIDTO) {
        recordService.addRecord(recordIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Record added successfully"));
    }

    @PutMapping("/update/{recordId}")
    public ResponseEntity updateRecord(@PathVariable Integer recordId, @RequestBody @Valid RecordIDTO recordIDTO) {
        recordService.updateRecord(recordId, recordIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Record updated successfully"));
    }
}
