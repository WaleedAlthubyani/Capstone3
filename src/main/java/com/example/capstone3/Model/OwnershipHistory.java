package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Period;
//Mshari
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OwnershipHistory {//mishari
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Owner is required")
    @Column(nullable = false)
    private String owner;

    @NotNull(message = "Ownership period is required")
    @Column(nullable = false)
    private Period ownershipPeriod;

    @ManyToOne
    @JsonIgnore
    private Record record;
}
