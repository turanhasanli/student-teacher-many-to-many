package com.example.demo.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class StudentAndTeachersDto {

    private String name;
    private Set<TeacherResponseDto> teachers;

}
