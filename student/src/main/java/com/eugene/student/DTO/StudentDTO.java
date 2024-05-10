package com.eugene.student.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class StudentDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("last_name")
    private String lastname;
    private int universityId;
}
