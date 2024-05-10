package com.eugene.student.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eugene.student.DTO.PostStudent;
import com.eugene.student.DTO.StudentDTO;
import com.eugene.student.DTO.StudentFullInformationDTO;
import com.eugene.student.services.StudentService;

import lombok.RequiredArgsConstructor;

// TODO: /сделать переменную версия api в applicatiom.yml
 
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/test")
    public String testMeth() {
        return "test_meth";
    }

    @GetMapping()
    public List<StudentDTO> getAll() {
        return this.studentService.getAllDto();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> postStudent(@RequestBody StudentDTO studentDTO) {
        boolean isSaved = this.studentService.saveStudent(studentDTO);
        if(isSaved)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/with-university")
    public ResponseEntity<StudentFullInformationDTO> postStudentWithUnibersityName(
        @RequestBody PostStudent postStudent) {

        StudentFullInformationDTO fullInformationDTO = this.studentService
            .saveStudentWithUniversity(postStudent);
        
        if(fullInformationDTO != null)
            return new ResponseEntity<>(fullInformationDTO, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/by-university/{id}")
    public List<StudentDTO> getStudentsByUniversityId(@PathVariable("id") int id) {
        return this.studentService.getStudentsByUniversityId(id);
    }

    @GetMapping("/search")
    public ResponseEntity<StudentFullInformationDTO> getStudentFullInformation(
        @RequestParam("name") String name,
        @RequestParam("last-name") String lastName
    ) {
        StudentFullInformationDTO fullInformationDTO = this
            .studentService.getStudentInformationByName(name, lastName);
        if(fullInformationDTO != null)
            return ResponseEntity.ok().body(fullInformationDTO);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/search-with-response")
    public ResponseEntity<StudentFullInformationDTO> getStudentFullInformationWithResponse(
        @RequestParam("name") String name,
        @RequestParam("last-name") String lastName
    ) {
        StudentFullInformationDTO fullInformationDTO = this
            .studentService.getStudentInformationWithResponse(name, lastName);
        if(fullInformationDTO != null)
            return ResponseEntity.ok().body(fullInformationDTO);
        else
            return ResponseEntity.notFound().build();
    }

}
