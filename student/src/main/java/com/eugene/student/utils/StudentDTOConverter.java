package com.eugene.student.utils;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.eugene.student.DTO.PostStudent;
import com.eugene.student.DTO.StudentDTO;
import com.eugene.student.DTO.StudentFullInformationDTO;
import com.eugene.student.DTO.UniversityDTO;
import com.eugene.student.models.Student;

public class StudentDTOConverter {
    private ModelMapper modelMapper;

    public StudentDTOConverter() {
        this.modelMapper = new ModelMapper();
    }

    public StudentDTO toDto(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    public Student toModel(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setLastname(studentDTO.getLastname());
        student.setUniversityId(studentDTO.getUniversityId());
        return student;
    }

    public Student toModel(PostStudent postStudent, int universityId) {
        Student student = new Student();
        student.setName(postStudent.getName());
        student.setLastname(student.getLastname());
        student.setUniversityId(universityId);
        return student;
    }

    public List<StudentDTO> toDtoList(List<Student> list) {
        return list.stream()
            .map((t)->modelMapper.map(t, StudentDTO.class)).toList();
    }

    public StudentFullInformationDTO toFullInformationDTO(Student student, UniversityDTO universityDTO) {
        StudentFullInformationDTO studentFullInformationDTO = new StudentFullInformationDTO();
        studentFullInformationDTO.setName(student.getName());
        studentFullInformationDTO.setLastName(student.getLastname());
        studentFullInformationDTO.setUniversity(universityDTO);
        return studentFullInformationDTO;
    }
}
