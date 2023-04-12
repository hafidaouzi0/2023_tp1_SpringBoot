package com.example.demo.student;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StudentService {

    Student addStudent(Student student) throws Exception ;
    Student updateStudent(Student student) throws Exception;
    Student deleteStudent(Long id) throws Exception;
    Student getStudent(Long id) throws Exception;
    List<Student> getAllStudents() throws Exception;
    //on peut recuperer des pages au lieud"une liste qui contient des milliers d'enregistrements
    //peageable nous permet de definir le nombre et la taille de la page
    Page<Student> getStudentsAsPage(Pageable pageable) throws Exception;
Student getStudentByEmail(String email) throws Exception;

Page<Student> getStudentByKeyword(String keyword,Pageable pageable) throws Exception;

}
