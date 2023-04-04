package com.example.demo.student;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        try {
            return studentService.addStudent(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
   public List<Student> getAll(){
       try {
           return  studentService.getAllStudents();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

   @GetMapping("/{email}")
   public Student getByEmail(@PathVariable(name = "email") String email){

       try {
           return studentService.getStudentByEmail(email);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

   }

@DeleteMapping("/{id}")
   public Student deleteStudent(@PathVariable(name = "id") Long id){
       try {
           return studentService.deleteStudent(id);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }


}
