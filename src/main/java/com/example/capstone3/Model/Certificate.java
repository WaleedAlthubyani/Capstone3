package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Type is required")
    @Column(nullable = false)
    private String type;

    @PastOrPresent
    @NotNull(message = "Giving date is required")
    @Column(nullable = false)
    private LocalDate givingDate;

    @NotNull(message = "Expiration date is required")
    @FutureOrPresent(message = "Expiration date must be in the future")
    @Column(nullable = false)
    private LocalDate expirationDate;

    @NotEmpty(message = "Registration number is required")
    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @NotEmpty(message = "URL is required")
    @URL(message = "URL should be in the right form")
    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JsonIgnore
    private Artifact artifact;
}
