package com.example.capstone3.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
//Waleed
@Data
@AllArgsConstructor
public class RequestIDTO {

    @NotNull(message = "Please enter a start date")
    @FutureOrPresent(message = "start date can't be in the past")
    private LocalDate startDate;

    @NotNull(message = "Please enter an end date")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;
}
