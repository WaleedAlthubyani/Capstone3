package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a name")
    @Size(max = 100,message = "Name can't be longer than 100 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email address")
    @Size(max = 100,message = "Email can't be longer than 100 characters")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @NotEmpty(message = "Please enter a password")
    @Size(max = 50,message = "password can't be longer than 50 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",message = "Password must contain at least one digit [0-9].Password must contain at least one lowercase Latin character [a-z].Password must contain at least one uppercase Latin character [A-Z].Password must contain at least one special character like ! @ # & ( ).Password must contain a length of at least 8 characters and a maximum of 20 characters.")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

}
