package com.example.demo.student;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class StudentConfig {

    //on va creer qlq utilisateurs students pour tester le comportement de notre application
    private final StudentRepository studentRepository;


   // @Bean //il faut l activer si on veut que ces args soient executés et par la suite les students vont se creer
    CommandLineRunner initStudent(){

        return args -> {
           studentRepository.save(new Student(
                   null,
                   "hafida",
                   "hafida@gmail.com",
                   LocalDate.of(2000,9,25)

           ));
            studentRepository.save(new Student(
                    null,
                    "Mohammed",
                    "Mohammed@gmail.com",
                    LocalDate.of(2001,1,23)

            )); studentRepository.save(new Student(
                    null,
                    "Bader",
                    "Bader@gmail.com",
                    LocalDate.of(2000,9,25)

            ));
            studentRepository.save(new Student(
                    null,
                    "safia",
                    "safia@gmail.com",
                    LocalDate.of(2008,4,4)

            ));
         //   List<Student> students=studentRepository.findStudentByQuery("sa");
         //   System.out.println(students);

            //methode de reference new dans java 8
          //  studentRepository.findAll().forEach(System.out::println);
           // List<Student> students=studentRepository.findStudentByEmail("hafida@gmail.com");
         //   System.out.println(students);
          //  System.out.println(students.get(0));

        };


    }


}
