package com.eugene.university.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UniversityStudentDTO {
    @JsonProperty("university_name")
    private String name;

    @JsonProperty("university_domain")
    private String domain;

    @JsonProperty("students")
    List<StudentDTO> studentList;
}
