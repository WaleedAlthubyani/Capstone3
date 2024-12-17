package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Period;

@Data
@AllArgsConstructor
public class OwnershipHistoryIDTO {

    private String owner;
    private Period ownershipPeriod;
    private Integer recordId;
}
