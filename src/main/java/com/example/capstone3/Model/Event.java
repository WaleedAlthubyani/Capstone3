package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name is mandatory")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;
    @NotEmpty(message = "description is mandatory")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "startDate must be Future Or Present")
    @Column(columnDefinition = "datetime")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "end Date must be Future Or Present")
    @Column(columnDefinition = "datetime")
    private LocalDate endDate;
    @NotEmpty(message = "location is empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @ManyToOne
    @JsonIgnore
    private Organization organization;
    @OneToMany(mappedBy = "event")
    private Set<Artifact> artifacts;

    @ManyToMany(mappedBy = "events")
    private Set<Notification> notifications;
}
