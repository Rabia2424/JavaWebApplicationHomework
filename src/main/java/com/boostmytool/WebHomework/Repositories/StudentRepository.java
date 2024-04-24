package com.boostmytool.WebHomework.Repositories;

import com.boostmytool.WebHomework.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
        @Modifying
        @Transactional
        @Query(value="delete from Student u where u.id= ?1")
        void deleteById(int id);
}
