package com.boostmytool.WebHomework.Controllers;

import com.boostmytool.WebHomework.Models.Lecture;
import com.boostmytool.WebHomework.Repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class LectureController {
    private LectureRepository lr;

    public LectureController(LectureRepository lectureRepository){
        lr=lectureRepository;
    }

    @GetMapping("/lectures")
    public String showLectureList(Model model){
        List<Lecture> lectures = lr.findAll();
        model.addAttribute("lectures",lectures);
        return "/lectures/index";
    }

    @GetMapping("/lectures/add")
    public String showLectureForm(Model model){
        Lecture lecture = new Lecture();
        model.addAttribute("lecture",lecture);
        return "/lectures/add";
    }

    @PostMapping("/lectures/add")
    public String addLecture(Lecture lecture){
        if(lecture == null) {
            System.out.println("Lecture is null");
            return "redirect:/lectures";
        }
        lr.save(lecture);
        return "redirect:/lectures";
    }

    @GetMapping("/lectures/update/{id}")
    public String updateLecture(@PathVariable int id, Model model){
        Lecture lecture = lr.getById(id);
        model.addAttribute("findLecture",lecture);
        return "/lectures/update";
    }

    @PostMapping("/lectures/update/{id}")
    public String lectureUpdate(@PathVariable int id, Lecture lecture){
        Lecture model = lr.getById(id);
        model.setName(lecture.getName());
        model.setCredit(lecture.getCredit());
        lr.save(model);
        return "redirect:/lectures";
    }

    @GetMapping("/lectures/delete/{id}")
    public String deleteLecture(@PathVariable int id,Model model){
        Lecture lecture = lr.getById(id);
        model.addAttribute("lecture",lecture);
        return "/lectures/delete";
    }

    @PostMapping("/lectures/delete/{id}")
    public String lectureDelete(Lecture lecture){
        lr.delete(lecture);
        return "redirect:/lectures";
    }
}
