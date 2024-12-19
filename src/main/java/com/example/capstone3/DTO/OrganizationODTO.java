package com.example.capstone3.DTO;

import com.example.capstone3.Model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
//Bayan
@Data
@AllArgsConstructor
public class OrganizationODTO {
    private String name;
    private String type;
    private String phoneNumber;
    private String email;
    private Set<Event> events;
    private LocalDate createdAt;

}
