package com.eugene.university.controllers;

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

import com.eugene.university.DTO.UniversityDTO;
import com.eugene.university.DTO.UniversityStudentDTO;
import com.eugene.university.services.UniversityService;

@RestController
@RequestMapping("/api/v1/university")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public List<UniversityDTO> getAll() {
        return this.universityService.getAllUniversityDTOs();
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> postUniversity(
        @RequestBody UniversityDTO universityDTO) {
        
        boolean isSaved = this.universityService.saveUniversity(universityDTO);
        if(isSaved)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public UniversityDTO getUniversityById(@PathVariable("id") int id) {
        return this.universityService.getUniversityById(id);
    }

    @GetMapping("/with-response/{id}")
    public ResponseEntity<UniversityDTO> getUniversityByIdResponse(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(this.universityService.getUniversityById(id));
    }

    @GetMapping("/with-students/{id}")
    public UniversityStudentDTO getUniversityByIdWithStudents(
        @PathVariable("id") int id
    ) {
        return this.universityService.getUniversityByIdStudentDTOs(id);
    }

    @GetMapping("/with-students")
    public ResponseEntity<List<UniversityStudentDTO>> getUniversitiesWithStudents() {
        List<UniversityStudentDTO> list = this.universityService.getAllUniversityStudentDTOs();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/with-students-by-name/{hei_name}")
    public ResponseEntity<UniversityStudentDTO> getUniversityStudentByUniversityName(
        @PathVariable("hei_name") String heiName
    ) {
        int id = this.universityService.getIdByUniversityName(heiName);
        UniversityStudentDTO universityStudentDTO = this.universityService.getUniversityByIdStudentDTOs(id);
        
        return new ResponseEntity<>(universityStudentDTO, HttpStatus.OK);
    }

    @GetMapping("/university-id-by-name")
    public ResponseEntity<Integer> getUniversityIdByName(@RequestParam("name") String name) {
        Integer id = this.universityService.getIdByUniversityName(name);
        if(id != null)
            return ResponseEntity.ok(id);
        else
            return ResponseEntity.notFound().build();
    }
}
