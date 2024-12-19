package com.example.capstone3.DTO;

import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Model.Researcher;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
//Bayan
@Data
@AllArgsConstructor
public class FeedbackDTO {
    @NotEmpty(message = "comment is mandatory")
    private String comment;

    @NotNull(message = "rating is mandatory")
    @Min(value = 1, message = "rating must be 1 or more")
    @Max(value = 5, message = "rating must be 5 or less")
    private Integer rating;

    private String creatorType;

    private Integer creatorId;

    private String senderEmail;

    private String receiverEmail;

    private Contributor contributor;
    private Researcher researcher;
    private Organization organization;
}
