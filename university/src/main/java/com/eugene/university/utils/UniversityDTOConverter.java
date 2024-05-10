package com.eugene.university.utils;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.eugene.university.DTO.StudentDTO;
import com.eugene.university.DTO.UniversityDTO;
import com.eugene.university.DTO.UniversityId;
import com.eugene.university.DTO.UniversityStudentDTO;
import com.eugene.university.models.University;

public class UniversityDTOConverter {
    private ModelMapper modelMapper;

    public UniversityDTOConverter() {
        this.modelMapper = new ModelMapper();
    }

    public UniversityDTO toDto(University university) {
        return this.modelMapper.map(university, UniversityDTO.class);
    }

    public University toModel(UniversityDTO universityDTO) {
        University university = new University();
        university.setName(universityDTO.getName());
        university.setDomain(universityDTO.getDomain());
        return university;
    }

    public List<UniversityDTO> toDtoList(List<University> list) {
        return list.stream()
            .map((t)->modelMapper.map(t, UniversityDTO.class)).toList();
    }

    public UniversityStudentDTO toDtoWithStudents(University university, List<StudentDTO> list) {
        UniversityDTO universityDTO = toDto(university);
        UniversityStudentDTO universityStudentDTO = new UniversityStudentDTO(universityDTO.getName(), 
            universityDTO.getDomain(), list);
        return universityStudentDTO;
    }

    public UniversityId toUniversityId(int id) {
        return new UniversityId(id);
    }
}
