package com.boostmytool.WebHomework.Repositories;

import com.boostmytool.WebHomework.DTOs.RegisterView;
import com.boostmytool.WebHomework.Models.StudentLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLectureRepository extends JpaRepository<StudentLecture,Integer> {
}
