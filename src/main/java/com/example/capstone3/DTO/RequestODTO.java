package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestODTO {

    private Object requester;

    private String type;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String decision;

    private LocalDateTime createdAt;
}
