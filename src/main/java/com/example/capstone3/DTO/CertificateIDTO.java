package com.example.capstone3.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CertificateIDTO {


    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Type is required")
    private String type;

    @PastOrPresent
    @NotNull(message = "Giving date is required")
    private LocalDate givingDate;

    @NotNull(message = "Expiration date is required")
    @FutureOrPresent(message = "Expiration date must be in the future")
    private LocalDate expirationDate;

    @NotEmpty(message = "Registration number is required")
    private String registrationNumber;

    @NotEmpty(message = "URL is required")
    @URL(message = "URL should be in the right form")
    private String url;

    @NotNull(message = "Provide an artifact id")
    private Integer artifactId;
}
