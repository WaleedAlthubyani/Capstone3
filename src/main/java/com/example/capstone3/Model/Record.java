package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

//Mshari
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Record {//mishari

    @Id
    private Integer id;

    @NotEmpty(message = "Purpose is required")
    @Column(nullable = false)
    private String purpose;

    @NotEmpty(message = "description is required")
    @Column(nullable = false)
    private String description;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Artifact artifact;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OwnershipHistory> ownershipHistories;
}
