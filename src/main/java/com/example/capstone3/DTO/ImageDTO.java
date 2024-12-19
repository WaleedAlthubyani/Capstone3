package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
//Mshari
@Data
@AllArgsConstructor
public class ImageDTO {

    @NotEmpty(message = "URL is required")
    @URL(message = "URL should be in correct form")
    private String url;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "artifactId is required")
    private Integer artifactId;

}
