package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor//ajoute l'injection automatiquement sans creer manuellemnt le constructeur pour l'objet qu'on veut injecter dans notre cas c'est StudentService
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name ="size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        //controlling request...
        try {
         //   List<Student> students=studentService.getAllStudents();
            Page<Student> students=studentService.getStudentByKeyword(
                   keyword, PageRequest.of(page,size)
            );
          /*  Page<Student> students=studentService.getStudentsAsPage(
                   PageRequest.of(page,size)
          );*/
            model.addAttribute("students",students);
            //renvoyer nombre de pages
            model.addAttribute("pages",new int[students.getTotalPages()]);
            model.addAttribute("currentPage",page);
            model.addAttribute("keyword",keyword);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());

        }
        //returning the view
        return "students";
    }

/*
    @PostMapping
    public String Search(@PathVariable String keyword,Model model){

        try {
            Page<Student> students=studentService.getStudentByKeyword(keyword,PageRequest.of(0,5));
            model.addAttribute("students",students);
            //renvoyer nombre de pages
            model.addAttribute("pages",new int[students.getTotalPages()]);
            model.addAttribute("currentPage",0);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }
        return "students";
    }*/
}
