package com.boostmytool.WebHomework.Controllers;

import com.boostmytool.WebHomework.DTOs.RegisterView;
import com.boostmytool.WebHomework.Models.Lecture;
import com.boostmytool.WebHomework.Models.Student;
import com.boostmytool.WebHomework.Models.StudentLecture;
import com.boostmytool.WebHomework.Repositories.LectureRepository;
import com.boostmytool.WebHomework.Repositories.StudentLectureRepository;
import com.boostmytool.WebHomework.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StudentLectureController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    private StudentRepository studentRepository;
    private LectureRepository lectureRepository;
    private StudentLectureRepository studentLectureRepository;

    public StudentLectureController(StudentRepository sr, LectureRepository lr, StudentLectureRepository slr) {
        this.studentRepository = sr;
        this.lectureRepository = lr;
        this.studentLectureRepository = slr;
    }

    @GetMapping("/register/students/{id}")
    public String showRegisterForm(@PathVariable int id, Model model) {
        model.addAttribute("registerView", new RegisterView());
        Student student = studentRepository.getById(id);
        List<Lecture> lectures = lectureRepository.findAll();
        model.addAttribute("student", student);
        model.addAttribute("lectures", lectures);
        return "/register/studentIndex";
    }

    @PostMapping("/register/students/{id}")
    public String addRegister(@PathVariable int id, RegisterView registerView){
        Student student = studentRepository.getById(id);
        Lecture lecture = lectureRepository.getById(registerView.getLecture().getId());
        StudentLecture sl = new StudentLecture();
        sl.setStudent(student);
        sl.setLecture(lecture);
        sl.setRegister_date(registerView.getRegister_date());
        sl.setSemester(registerView.getSemester());
        studentLectureRepository.save(sl);
        return "redirect:/register";
    }

    @GetMapping("/register/lectures/{id}")
    public String showRegisterFormForLecture(@PathVariable int id,Model model){
        Lecture lecture = lectureRepository.getById(id);
        List<Student> students = studentRepository.findAll();
        model.addAttribute("lecture",lecture);
        model.addAttribute("students",students);
        model.addAttribute("registerView",new RegisterView());
        return "/register/lectureIndex";
    }

    @PostMapping("/register/lectures/{id}")
    public String addRegisterForLecture(RegisterView registerView){
        StudentLecture sl = new StudentLecture();
        sl.setStudent(registerView.getStudent());
        sl.setLecture(registerView.getLecture());
        sl.setRegister_date(registerView.getRegister_date());
        sl.setSemester(registerView.getSemester());
        studentLectureRepository.save(sl);
        return "redirect:/register";
    }

    @GetMapping("/register")
    public String showRegisterList(Model model) {
        List<StudentLecture> registrations = studentLectureRepository.findAll();
        model.addAttribute("registrations", registrations);
        return "/register/registerList";
    }

    @GetMapping("/register/update/{id}")
    public String updateRegister(@PathVariable int id,Model model){
        List<Lecture> lectures = lectureRepository.findAll();
        model.addAttribute("lectures",lectures);
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students",students);
        StudentLecture sl = studentLectureRepository.getById(id);
        RegisterView rw = new RegisterView();
        rw.setId(sl.getId());
        rw.setStudent(sl.getStudent());
        rw.setLecture(sl.getLecture());
        rw.setRegister_date(sl.getRegister_date());
        rw.setSemester(sl.getSemester());
        model.addAttribute("registerView",rw);
        return "/register/update";
    }

    @PostMapping("/register/update/{id}")
    public String registerUpdate(@PathVariable int id,RegisterView registerView){
        StudentLecture sl = studentLectureRepository.getById(id);
        sl.setLecture(registerView.getLecture());
        sl.setStudent(registerView.getStudent());
        sl.setRegister_date(registerView.getRegister_date());
        sl.setSemester(registerView.getSemester());
        studentLectureRepository.save(sl);
        return "redirect:/register";
    }

    @GetMapping("/register/delete/{id}")
    public String deleteRegister(@PathVariable int id,Model model){
        StudentLecture sl = studentLectureRepository.getById(id);
        RegisterView rw = new RegisterView();
        rw.setId(sl.getId());
        rw.setStudent(sl.getStudent());
        rw.setLecture(sl.getLecture());
        rw.setRegister_date(sl.getRegister_date());
        rw.setSemester(sl.getSemester());
        model.addAttribute("delregisterView",rw);
        return "/register/delete";
    }

    @PostMapping("/register/delete/{id}")
    public String registerDelete(@PathVariable int id){
        studentLectureRepository.deleteById(id);
        return "redirect:/register";
    }

    /*@GetMapping("/register/delete/{id}")
    public String registerDelete(@PathVariable int id){
        studentLectureRepository.deleteById(id);
        return "redirect:/register";
    }*/

}

