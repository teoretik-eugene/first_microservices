package com.eugene.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.eugene.student.DTO.UniversityDTO;
import com.eugene.student.DTO.UniversityId;


@FeignClient(name = "university", url = "http://localhost:8070/api/v1/university")
public interface UniversityClient {

    @GetMapping("/{id}")
    public UniversityDTO getUniversityById(@PathVariable("id") int id);

    @GetMapping("/with-response/{id}")
    public ResponseEntity<UniversityDTO> getUniversityByIdResponse(@PathVariable("id") int id);

    @GetMapping("/university-id-by-name")
    public ResponseEntity<UniversityId> getUniversityIdByName(@RequestParam("name") String name);

}
