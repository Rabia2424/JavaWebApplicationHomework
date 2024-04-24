package com.boostmytool.WebHomework.DTOs;

import com.boostmytool.WebHomework.Models.Lecture;
import com.boostmytool.WebHomework.Models.Student;

import java.util.Date;

public class RegisterView {
    private int id;
    private Lecture lecture;
    private Student student;
    private Date register_date;
    private String semester;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }
}
