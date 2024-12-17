package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResearcherODTO {

        private String name;

        private String email;

        private String phoneNumber;

        private String fieldOfStudy;

        private String licenseURL;
}
