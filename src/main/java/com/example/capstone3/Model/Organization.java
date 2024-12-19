package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.util.Set;
//Bayan
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "(status='pending' or status='approved' or status='rejected')")
public class Organization {//bayan
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name is mandatory")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;
    @NotEmpty(message = "type is mandatory")
    @Column(columnDefinition = "varchar(50) not null")
    private String type;
    @NotEmpty(message = "phoneNumber is mandatory")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;
    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is mandatory")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotEmpty(message = "Password is mandatory")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "created date must be Future Or Present")
    @Column(columnDefinition = "datetime")
    private LocalDate createdAt;
    @Column(columnDefinition = "varchar(8) not null")
    private String status="pending";
    @JsonIgnore
    @Column(columnDefinition = "boolean not null")
    private Boolean isBanned=false;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "organization")
    private Set<Event> events;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private Set<Request> requests;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private Set<Feedback> feedbacks;
    @OneToMany(mappedBy = "organization")
    @JsonIgnore
    private Set<Report> reports;
//    @ManyToOne
//    @JsonIgnore
//    private BanList banList;
}
