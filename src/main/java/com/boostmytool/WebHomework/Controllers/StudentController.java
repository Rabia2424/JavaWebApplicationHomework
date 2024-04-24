package com.boostmytool.WebHomework.Controllers;

import com.boostmytool.WebHomework.Models.Student;
import com.boostmytool.WebHomework.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentRepository repo;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        repo = studentRepository;
    }

    @GetMapping({"", "/"})
    public String showStudentList(Model model){
        List<Student> students = repo.findAll();
        model.addAttribute("students",students);
        return "students/index";
    }

    @GetMapping("/add")
    public String showStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("addstudent", student);
        return "students/add";
    }

    @PostMapping("/add")
    public String addStudent(Student student){
        if(student==null){
            System.out.println("Student is null");
            return "redirect:/students";
        }
        repo.save(student);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") int id, Model model){
        Student student2 = repo.getById(id);
        model.addAttribute("user",student2);
        return "/students/update";
    }

//    @PostMapping("/update/{id}")
//    public String studentUpdate(@PathVariable("id") int id, Student student){
//        Student model = repo.getById(id);
//        model.setName(student.getName());
//        model.setSurname(student.getSurname());
//        repo.save(model);
//        return "redirect:/students";
//    }

    @PostMapping("/update/{id}")
    public String studentUpdate(Student student){
        repo.save(student);
        return "redirect:/students";
    }

    /*@PostMapping("/update/{id}")
    public String studentUpdate(@PathVariable("id") int id, @RequestParam String name, @RequestParam String surname){
        Student student = repo.getById(id);
        student.setName(name);
        student.setSurname(surname);
        repo.save(student);
        return "redirect:/students";
    }*/

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model){
        Student student = repo.getById(id);
        model.addAttribute("delstudent",student);
        return "/students/delete";
    }

    @PostMapping("/delete/{id}")
    public String studentDelete(Student student){
        repo.delete(student);
        return "redirect:/students";
    }
}
