package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Check;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "(status='pending' or status='approved' or status='rejected')")
public class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String era;

    @Column(nullable = false)
    private String location;

    @Column(name = "artifact_condition",nullable = false)
    private String condition;

    @AssertTrue
    @Column
    private Boolean availability;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Record record;

    @Column(columnDefinition = "varchar(8) not null")
    private String status="pending";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artifact")
    private Set<Image> images;

    @ManyToMany(mappedBy = "artifacts")
    private Set<Tag> tags;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artifact")
    private Set<Certificate> certificates;

    @ManyToOne
    @JsonIgnore
    private Contributor contributor;

    @ManyToMany
    @JsonIgnore
    private Set<Researcher> researchers;

    @ManyToOne
    @JsonIgnore
    private Event event;

}
