package com.example.demo.student;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor //pour l'injection
public class StudentServiceImp implements  StudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) throws Exception {
        //on ajoute les containtes  meties dans cahiers de charge avant d'enregistrer student
        if(student!=null)
         return studentRepository.save(student);
         else
             throw new RuntimeException("student cannot be added");
    }

    @Override
    public Student updateStudent(Student student) throws Exception {
        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudent(Long id) throws Exception {
        Student student=getStudent(id);
      //  studentRepository.delete(student);
        studentRepository.deleteById(id);

        return student;

    }

    @Override
    public Student getStudent(Long id) throws Exception {
        if(studentRepository.findById(id).isPresent())
         return studentRepository.findById(id).get();
        else throw new RuntimeException("student not found");
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> getStudentsAsPage(Pageable pageable) throws Exception {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentByEmail(String email) throws Exception {
        List<Student> students=studentRepository.findStudentByEmail(email);
        if(students.size()>0)
         return  students.get(0);
        else
            throw new RuntimeException("student not found");
    }
}
