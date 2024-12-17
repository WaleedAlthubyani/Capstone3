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

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Purpose is required")
    @Column(nullable = false)
    private String purpose;

    @PastOrPresent(message = "Request date should be in the past or the present")
    @NotNull(message = "Request date is required")
    @Column(nullable = false)
    private LocalDate requestDate;

    @PastOrPresent(message = "approval date should be in the past or the present")
    @NotNull(message = "Approval date is required")
    @Column(nullable = false)
    private LocalDate approvalDate;

    @PastOrPresent(message = "return date should be in the past or the present")
    @NotNull(message = "Return date is required")
    @Column(nullable = false)
    private LocalDate returnDate;

    @NotEmpty(message = "Requester name is required")
    @Column(nullable = false)
    private String requester;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Artifact artifact;
}
