package com.example.capstone3.DTO;

import com.example.capstone3.Model.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ArtifactIDTO {

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Type is required")
    private String type;

    @NotEmpty(message = "Origin is required")
    private String origin;

    @NotEmpty(message = "Era is required")
    private String era;

    @NotEmpty(message = "Location is required")
    private String location;

    @NotEmpty(message = "Condition is required")
    private String condition;

    @NotNull(message = "Category ID is required")
    private Integer categoryId;

    @NotNull(message = "Contributor ID is required")
    private Integer contributorId;
}
