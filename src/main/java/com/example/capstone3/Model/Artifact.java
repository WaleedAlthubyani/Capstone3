package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Type is required")
    @Column(nullable = false)
    private String type;

    @NotEmpty(message = "Origin is required")
    @Column(nullable = false)
    private String origin;

    @NotEmpty(message = "Era is required")
    @Column(nullable = false)
    private String era;

    @NotEmpty(message = "Location is required")
    @Column(nullable = false)
    private String location;

    @NotEmpty(message = "Condition is required")
    @Column(nullable = false)
    private String condition;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Record record;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artifact")
    private Set<Image> images;

    @ManyToMany(mappedBy = "artifacts")
    private Set<Tag> tags;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artifact")
    private Set<Certificate> certificates;
}
