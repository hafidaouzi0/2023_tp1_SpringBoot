package com.example.demo.student;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
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
