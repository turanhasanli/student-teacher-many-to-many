package com.example.demo.model.dto;

import com.example.demo.model.enums.Course;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponseDto {

    private String name;
    private String lastname;
    private Course course;

}
