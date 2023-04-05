package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor//ajoute l'injection automatiquement sans creer manuellemnt le constructeur pour l'objet qu'on veut injecter dans notre cas c'est StudentService
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name ="size",defaultValue = "5") int size){
        //controlling request...
        try {
         //   List<Student> students=studentService.getAllStudents();
            Page<Student> students=studentService.getStudentsAsPage(
                   PageRequest.of(page,size)
          );
            model.addAttribute("students",students);
            //renvoyer nombre de pages
            model.addAttribute("pages",new int[students.getTotalPages()]);
            model.addAttribute("currentPage",page);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());

        }
        //returning the view
        return "students";
    }
}
