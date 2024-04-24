package com.boostmytool.WebHomework.Repositories;

import com.boostmytool.WebHomework.Models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture,Integer> {
}
