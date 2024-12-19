package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
//Waleed
@Data
@AllArgsConstructor
public class RequestODTO {

    private Object requester;

    private String type;

    private LocalDate startDate;

    private LocalDate endDate;

    private String decision;

    private LocalDateTime createdAt;
}
