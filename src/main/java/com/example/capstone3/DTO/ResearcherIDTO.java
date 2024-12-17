package com.example.capstone3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
public class ResearcherIDTO {

    @NotEmpty(message = "Please enter a name")
    @Size(max = 100,message = "Name can't be longer than 100 characters")
    private String name;

    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email address")
    @Size(max = 100,message = "Email can't be longer than 100 characters")
    private String email;

    @NotEmpty(message = "Please enter a password")
    @Size(max = 50,message = "password can't be longer than 50 characters")
    private String password;

    @NotEmpty(message = "Please enter a phone number")
    @Pattern(regexp = "^05[0-9]{8}$",message = "Phone number must be 10 digits long starting with 05")
    private String phoneNumber;

    @NotEmpty(message = "Please enter a field of study")
    @Size(max = 50,message = "Field of study can't be longer than 50 characters")
    private String fieldOfStudy;

    @NotEmpty(message = "please enter your license URL")
    @URL(message = "Please enter a valid URL")
    @Size(max = 255,message = "URL can't be longer than 255 characters")
    private String licenseURL;
}
