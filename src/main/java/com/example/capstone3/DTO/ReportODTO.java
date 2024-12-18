package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReportODTO {
    private Integer sender;
    private Integer offender;
    private String reason;
    private String status;
    private LocalDate createdAt;
    private String decision;

}
