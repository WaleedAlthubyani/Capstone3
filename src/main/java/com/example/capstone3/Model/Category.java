package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
//Mshari
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {//Mishari
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Artifact> artifacts;
}
