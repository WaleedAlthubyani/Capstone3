package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "(status='pending' or status='approved' or status='rejected')")
public class Researcher {//waleed

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(50) not null")
    private String fieldOfStudy;

    @Column(columnDefinition = "varchar(255) not null unique")
    private String licenseURL;

    @Column(columnDefinition = "varchar(8) not null")
    private String status="pending";

    @JsonIgnore
    @Column(columnDefinition = "boolean not null")
    private Boolean isBanned=false;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp not null")
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "researchers")
    private Set<Artifact> artifacts;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "researcher")
    private Set<Request> requests;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "researcher")
    private Set<Feedback> feedbacks;

    @ManyToMany(mappedBy = "researchers")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "researcher")
    @JsonIgnore
    private Set<Report> reports;

//    @ManyToOne
//    @JsonIgnore
//    private BanList banList;
}
