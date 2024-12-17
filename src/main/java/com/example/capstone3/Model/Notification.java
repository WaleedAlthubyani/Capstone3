package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "massage is required")
    @Column(nullable = false)
    private String massage;

    @NotNull(message = "timestamp")
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToMany
    @JsonIgnore
    private Set<Contributor> contributors;

    @ManyToMany
    @JsonIgnore
    private Set<Event> events;

    @ManyToMany
    @JsonIgnore
    private Set<Organization> organizations;

    @ManyToMany
    @JsonIgnore
    private Set<Researcher> researchers;

}
