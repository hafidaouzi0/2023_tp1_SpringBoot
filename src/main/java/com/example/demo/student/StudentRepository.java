package com.example.demo.student;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Long> {


//search student by keyword
     @Query("from Student s where s.name like %:keyword% or s.email like %:keyword% ")
     Page<Student> findStudentByKeyword(String keyword,Pageable pageable);
//deuxieme methode with spring  data

     Page<Student> findStudentByNameContainingOrEmailContaining(String name,String email,Pageable pageable);

     List<Student> findStudentByEmail(String email);

     @Query("from Student s where s.email like %:mc% and  s.name like %:mc% ")
     List<Student> findStudentByQuery(String mc);
}
