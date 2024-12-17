package com.example.capstone3.DTO;

import com.example.capstone3.Model.*;
import com.example.capstone3.Model.Record;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ArtifactODTO {

    private String name;

    private String type;

    private String origin;

    private String era;

    private String location;

    private String condition;

    private Boolean availability;

    private Set<Image> images;

    private Set<Tag> tags;

    private Category category;

    private Set<Certificate> certificates;

    private String contributorName;
}
