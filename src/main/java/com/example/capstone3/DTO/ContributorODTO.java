package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ContributorODTO {

    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate createdAt;
}
