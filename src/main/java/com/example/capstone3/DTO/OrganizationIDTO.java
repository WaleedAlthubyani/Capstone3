package com.example.capstone3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganizationIDTO {
    @NotEmpty(message = "name is mandatory")
    private String name;
    @NotEmpty(message = "type is mandatory")
    private String type;
    @NotEmpty(message = "phoneNumber is mandatory")
    private String phoneNumber;
    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is mandatory")
    private String email;
    @NotEmpty(message = "Password is mandatory")
    private String password;

}
