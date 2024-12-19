package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
//Bayan
@Data
@AllArgsConstructor
public class EventDTO {

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;



}
