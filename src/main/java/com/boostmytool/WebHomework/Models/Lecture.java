package com.boostmytool.WebHomework.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int credit;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<StudentLecture> studentLectures;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public int getCredit()
    {
        return credit;
    }

    public void setCredit(int credit)
    {
        this.credit=credit;
    }
}
