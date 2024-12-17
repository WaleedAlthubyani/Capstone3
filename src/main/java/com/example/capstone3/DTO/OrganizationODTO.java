package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrganizationODTO {
    private String name;
    private String type;
    private String phoneNumber;
    private String email;
    private LocalDate createdAt;

}
