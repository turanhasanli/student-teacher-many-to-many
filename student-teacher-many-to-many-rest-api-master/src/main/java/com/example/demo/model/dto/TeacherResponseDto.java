package com.example.demo.model.dto;


import com.example.demo.model.enums.Subject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherResponseDto {

    private String name;
    private String lastname;
    private Subject subject;

}
