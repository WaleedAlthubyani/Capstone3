package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {//bayan
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "sender is mandatory")
    @Column(columnDefinition = "int not null")
    private Integer sender;
    @NotNull(message = "offender is mandatory")
    @Column(columnDefinition = "int not null")
    private Integer offender;
    @NotEmpty(message = "reason is mandatory")
    @Column(columnDefinition = "varchar(100) not null")
    private String reason;
    @NotEmpty(message = "status is mandatory")
    @Pattern(regexp = "pending|accepted|rejected")
    @Column(columnDefinition = "varchar(20) not null")
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "datetime")
    private LocalDate createdAt;
    @NotEmpty(message = "decision is mandatory")
    @Column(columnDefinition = "varchar(100) not null")
    private String decision;
    @ManyToOne
    @JsonIgnore
    private Organization organization;
    @ManyToOne
    @JsonIgnore
    private Researcher researcher;
    @ManyToOne
    @JsonIgnore
    private Contributor contributor;

}
