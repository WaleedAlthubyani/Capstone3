package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedbackODTO {

    private String comment;
    private Integer rating;
    private String creatorType;
    private String creatorName;

}
