package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContributorIDTO {


    @NotEmpty(message = "Please enter a name")
    @Size(max = 100,message = "Name can't be longer than 100 characters")
    private String name;

    @NotEmpty(message = "Please enter an email")
    @Size(max = 100,message = "Email can't be longer than 100 characters")
    private String email;

    @NotEmpty(message = "Please enter a password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",message = "Password must contain at least one digit [0-9].Password must contain at least one lowercase Latin character [a-z].Password must contain at least one uppercase Latin character [A-Z].Password must contain at least one special character like ! @ # & ( ).Password must contain a length of at least 8 characters and a maximum of 20 characters.")
    private String password;

    @NotEmpty(message = "Please enter a phone number")
    @Pattern(regexp = "^05[0-9]{8}$",message = "Phone number must be 10 digits long starting with 05")
    private String phoneNumber;

}
