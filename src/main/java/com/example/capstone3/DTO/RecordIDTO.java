package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RecordIDTO {

    private Integer artifact_id;

    @NotEmpty(message = "Purpose is required")
    private String purpose;

    @NotEmpty(message = "description is required")
    private String description;

}
