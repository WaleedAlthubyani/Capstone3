package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

//Bayan
@Data
@AllArgsConstructor
public class ReportIDTO {
   private Integer sender;
    @NotEmpty(message = "reason is mandatory")
    private String reason;
    @NotEmpty(message = "reason is mandatory")
    private String offenderType;

}
