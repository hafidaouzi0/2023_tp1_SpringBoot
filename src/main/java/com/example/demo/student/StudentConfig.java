package com.example.demo.student;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


@Configuration
@RequiredArgsConstructor
public class StudentConfig {

    //on va creer qlq utilisateurs students pour tester le comportement de notre application
    private final StudentRepository studentRepository;


    @Bean
    CommandLineRunner initStudent(){

        return args -> {
           studentRepository.save(new Student(
                   null,
                   "hafida",
                   "hafida@gmaul.com",
                   LocalDate.of(2000,9,25)

           ));
        };
    }


}
