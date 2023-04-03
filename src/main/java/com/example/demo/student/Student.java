package com.example.demo.student;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Student {
    @SequenceGenerator(name = "my_sequence",
            sequenceName = "my_sequence",
            allocationSize = 1)

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "my_sequence")
    private Long code;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dateOfBirth;
}
