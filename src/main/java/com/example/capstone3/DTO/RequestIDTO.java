package com.example.capstone3.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestIDTO {

    @NotNull(message = "Please enter a start date")
    @FutureOrPresent(message = "start date can't be in the past")
    private LocalDateTime startDate;

    @NotNull(message = "Please enter an end date")
    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;
}
