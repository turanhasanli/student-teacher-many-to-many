package com.example.demo.model.dto;

import com.example.demo.model.dto.StudentResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TeacherAndStudentsDto {

    private String  name;
    private Set<StudentResponseDto> students;

}
