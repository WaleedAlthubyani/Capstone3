package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Period;

//Mshari
@Data
@AllArgsConstructor
public class OwnershipHistoryIDTO {

    @NotEmpty(message = "Owner is required")
    private String owner;

    @NotNull(message = "Ownership period is required")
    private Period ownershipPeriod;

    @NotNull(message = "Record ID is required")
    private Integer recordId;
}
