package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "comment is mandatory")
    @Column(columnDefinition = "varchar(100) not null")
    private String comment;

    @NotNull(message = "rating is mandatory")
    @Min(1)
    @Max(5)
    @Column(columnDefinition = "int not null")
    private Integer rating;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDate createdAt;

    @ManyToOne
    @JsonIgnore
    private Contributor contributor;

    @ManyToOne
    @JsonIgnore
    private Organization organization;

    @ManyToOne
    @JsonIgnore
    private Researcher researcher;
}
