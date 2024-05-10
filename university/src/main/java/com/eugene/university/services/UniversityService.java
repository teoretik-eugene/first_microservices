package com.eugene.university.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eugene.university.DTO.StudentDTO;
import com.eugene.university.DTO.UniversityDTO;
import com.eugene.university.DTO.UniversityStudentDTO;
import com.eugene.university.client.StudentClient;
import com.eugene.university.models.University;
import com.eugene.university.repository.UniversityRepository;
import com.eugene.university.utils.UniversityDTOConverter;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;
    private StudentClient studentClient;
    private UniversityDTOConverter universityDTOConverter;

    public UniversityService(UniversityRepository universityRepository, 
        StudentClient studentClient) {

        this.universityRepository = universityRepository;
        this.studentClient = studentClient;
        this.universityDTOConverter = new UniversityDTOConverter();
    }

    public List<University> getAll() {
        return universityRepository.findAll();
    }

    public List<UniversityDTO> getAllUniversityDTOs() {
        return this.universityDTOConverter.toDtoList(getAll());
    }

    public boolean saveUniversity(UniversityDTO universityDTO) {
        University university = this.universityDTOConverter.toModel(universityDTO);
        university = this.universityRepository.save(university);
        if(university != null)
            return true;
        else
            return false;
    }

    public UniversityDTO getUniversityById(int id) {
        Optional<University> optional = this.universityRepository.findById(id);
        if(optional.isPresent()) {
            return this.universityDTOConverter.toDto(optional.get());
        }
        return null;
    }

    public UniversityStudentDTO getUniversityByIdStudentDTOs(int id) {
        List<StudentDTO> studentDTOs = studentClient.findStudentsByUniversityId(id);
        Optional<University> optional = this.universityRepository.findById(id);
        if(optional.isPresent()) {
            University university = optional.get();
            return universityDTOConverter.toDtoWithStudents(university, studentDTOs);
        }
        return null;
    }

    public List<UniversityStudentDTO> getAllUniversityStudentDTOs() {
        List<University> universityList = getAll();
        List<UniversityStudentDTO> universityStudentDTOs = new ArrayList<>();

        ListIterator<University> listIterator = universityList.listIterator();

        while(listIterator.hasNext()) {
            University elem = listIterator.next();
            List<StudentDTO> studentDTOs = studentClient.findStudentsByUniversityId(elem.getId());
            universityStudentDTOs.add(universityDTOConverter.toDtoWithStudents(elem, studentDTOs));
        }

        return universityStudentDTOs;
    }

    public Integer getIdByUniversityName(String name) {
        Optional<University> optional = this.universityRepository.findByName(name);
        if(optional.isPresent()) {
            University university = optional.get();
            return university.getId();
        }

        return 0;
    }

}
