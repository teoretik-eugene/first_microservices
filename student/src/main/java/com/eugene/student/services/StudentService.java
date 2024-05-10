package com.eugene.student.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eugene.student.DTO.PostStudent;
import com.eugene.student.DTO.StudentDTO;
import com.eugene.student.DTO.StudentFullInformationDTO;
import com.eugene.student.DTO.UniversityDTO;
import com.eugene.student.DTO.UniversityId;
import com.eugene.student.client.UniversityClient;
import com.eugene.student.models.Student;
import com.eugene.student.repositories.StudentRepository;
import com.eugene.student.utils.StudentDTOConverter;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UniversityClient client;
    private StudentDTOConverter studentDTOConverter;

    public StudentService(StudentRepository studentRepository, UniversityClient client) {
        this.studentRepository = studentRepository;
        this.studentDTOConverter = new StudentDTOConverter();
        this.client = client;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<StudentDTO> getAllDto() {
        return studentDTOConverter.toDtoList(getAll());
    }

    public boolean saveStudent(StudentDTO studentDTO) {
        Student student = studentRepository.save(studentDTOConverter.toModel(studentDTO));
        if(student != null)
            return true;
        else
            return false;
    }

    public StudentFullInformationDTO saveStudentWithUniversity(PostStudent postStudent) {
        UniversityId universityId = this.client
            .getUniversityIdByName(postStudent.getUniversityName()).getBody();
        
        // Возвращает пустое значение если в запросе тоже пусто
        if(universityId == null)
            return null;
        
        Student student = this.studentDTOConverter
            .toModel(postStudent, universityId.getUniversityId());
        
        student = this.studentRepository.save(student);

        UniversityDTO universityDTO = this.client
            .getUniversityById(universityId.getUniversityId());
        
        StudentFullInformationDTO fullInformationDTO = this.studentDTOConverter
            .toFullInformationDTO(student, universityDTO);
        
        return fullInformationDTO;
    }

    public List<StudentDTO> getStudentsByUniversityId(int id) {
        return this.studentDTOConverter.toDtoList(this.studentRepository
            .findByUniversityId(id));
    }

    public StudentFullInformationDTO getStudentInformationByName(
        String name, 
        String lastName
    ) {
        Optional<Student> optional = this.studentRepository
        .findStudentByNameAndLastname(name, lastName);
        
        if(optional.isPresent()) {
            Student student = optional.get();
            UniversityDTO universityDTO = client.getUniversityById(student.getUniversityId());
            StudentFullInformationDTO fullInformation = this.studentDTOConverter
                .toFullInformationDTO(student, universityDTO);
            return fullInformation;
        }

        return null;
    }

    public StudentFullInformationDTO getStudentInformationWithResponse(
        String name,
        String lastName
    ) {
        Optional<Student> optional = this.studentRepository
        .findStudentByNameAndLastname(name, lastName);
        
        if(optional.isPresent()) {
            Student student = optional.get();
            UniversityDTO universityDTO = client
                .getUniversityByIdResponse(student.getUniversityId())
                .getBody();
            StudentFullInformationDTO fullInformation = this.studentDTOConverter
                .toFullInformationDTO(student, universityDTO);
            return fullInformation;
        }
        return null;
    }

}
