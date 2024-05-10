package com.eugene.student.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eugene.student.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    public List<Student> findByUniversityId(int universityId);

    public Optional<Student> findStudentByNameAndLastname(String name, String lastName);
}
