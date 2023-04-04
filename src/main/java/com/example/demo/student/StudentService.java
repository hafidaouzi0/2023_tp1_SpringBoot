package com.example.demo.student;


import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    Student addStudent(Student student) throws Exception ;
    Student updateStudent(Student student) throws Exception;
    Student deleteStudent(Long id) throws Exception;
    Student getStudent(Long id) throws Exception;
    List<Student> getAllStudents() throws Exception;
Student getStudentByEmail(String email) throws Exception;

}
