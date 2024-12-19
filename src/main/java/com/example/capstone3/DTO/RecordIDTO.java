package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecordIDTO {

    @NotNull(message = "Artifact ID is required")
    private Integer artifact_id;

    @NotEmpty(message = "Purpose is required")
    private String purpose;

    @NotEmpty(message = "description is required")
    private String description;

}
