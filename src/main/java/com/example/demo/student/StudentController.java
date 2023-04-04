package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor//ajoute l'injection automatiquement sans creer manuellemnt le constructeur pour l'objet qu'on veut injecter dans notre cas c'est StudentService
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public String index(Model model){
        //controlling request...
        try {
            List<Student> students=studentService.getAllStudents();
            model.addAttribute("students",students);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }
        //returning the view
        return "Students";
    }
}
