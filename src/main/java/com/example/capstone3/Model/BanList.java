package com.example.capstone3.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BanList {//bayan
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(255) not null")
    private String reason;

//    @OneToMany(mappedBy = "banList")
//    private Set<Organization> organizations;
//    @OneToMany(mappedBy = "banList")
//    private Set<Researcher> researchers;
//    @OneToMany(mappedBy = "banList")
//    private Set<Contributor> contributors;

    @Column(columnDefinition = "int null")
    private Integer organizationId;
    @Column(columnDefinition = "int null")
    private Integer researcherId;
    @Column(columnDefinition = "int null")
    private Integer contributorId;

}
