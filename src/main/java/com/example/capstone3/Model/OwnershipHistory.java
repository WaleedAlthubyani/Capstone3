package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OwnershipHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Owner is required")
    @Column(nullable = false)
    private String owner;

    @NotEmpty(message = "Ownership period is required")
    @Column(nullable = false)
    private Period ownershipPeriod;

    @ManyToOne
    @JsonIgnore
    private Record record;
}
