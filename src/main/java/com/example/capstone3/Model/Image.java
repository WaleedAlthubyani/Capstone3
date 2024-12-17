package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "URL is required")
    @URL(message = "URL should be in correct form")
    @Column(nullable = false)
    private String url;

    @NotEmpty(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JsonIgnore
    private Artifact artifact;
}
