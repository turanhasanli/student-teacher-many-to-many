package com.example.demo.mapper;

import com.example.demo.dao.entity.TeacherEntity;
import com.example.demo.model.dto.StudentResponseDto;
import com.example.demo.model.dto.TeacherAndStudentsDto;
import com.example.demo.model.dto.TeacherRequestDto;
import com.example.demo.model.dto.TeacherResponseDto;

import java.util.Set;
import java.util.stream.Collectors;

public class TeacherMapper {

    public static TeacherEntity mapRequestDtoToEntity(TeacherRequestDto dto) {
        return TeacherEntity.builder()
                .name(dto.getName())
                .lastname(dto.getLastname())
                .subject(dto.getSubject())
                .age(dto.getAge())
                .build();
    }

    public static TeacherResponseDto mapEntityToResponseDto(TeacherEntity entity) {
        return TeacherResponseDto.builder()
                .name(entity.getName())
                .lastname(entity.getLastname())
                .subject(entity.getSubject())
                .build();
    }

    public static TeacherAndStudentsDto mapEntityToTeacherAndStudentsDto(TeacherEntity entity){
        Set<StudentResponseDto> students = entity.getStudents().stream()
                .map(StudentMapper::mapEntityToResponseDto).collect(Collectors.toSet());
        return TeacherAndStudentsDto.builder()
                .name(entity.getName())
                .students(students)
                .build();
    }


}
