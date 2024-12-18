package com.example.capstone3.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedbackDTO {
    @NotEmpty(message = "comment is mandatory")
    private String comment;

    @NotNull(message = "rating is mandatory")
    @Min(1)
    @Max(5)
    private Integer rating;

    private String creatorType;

    private Integer creatorId;

    private Integer senderId;

    private Integer receiverId;
}
