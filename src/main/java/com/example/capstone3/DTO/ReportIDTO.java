package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportIDTO {
    @NotNull(message = "sender is mandatory")
    private Integer sender;
    @NotNull(message = "offender is mandatory")
    private Integer offender;
    @NotEmpty(message = "reason is mandatory")
    private String reason;

}
