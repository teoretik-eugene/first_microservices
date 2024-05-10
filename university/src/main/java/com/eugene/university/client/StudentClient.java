package com.eugene.university.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eugene.university.DTO.StudentDTO;

@FeignClient(name = "student-service", url = "${application.config.student-url}")
//@FeignClient(value = "student-service", path = "/api/v1/students")
public interface StudentClient {
    @GetMapping("/by-university/{id}")
    List<StudentDTO> findStudentsByUniversityId(@PathVariable("id") int id);
}
