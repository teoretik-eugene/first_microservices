package com.eugene.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eugene.university.models.University;


@Repository
public interface UniversityRepository extends JpaRepository<University, Integer>{
    public Optional<University> findByName(String name);
}
