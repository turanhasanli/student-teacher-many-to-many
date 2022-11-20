package com.example.demo.model.dto;

import com.example.demo.model.enums.Course;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRequestDto {

    private String name;
    private String lastname;
    private Integer age;
    private Course course;

}
