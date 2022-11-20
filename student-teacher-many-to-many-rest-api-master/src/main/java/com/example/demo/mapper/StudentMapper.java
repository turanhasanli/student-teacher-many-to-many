package com.example.demo.mapper;

import com.example.demo.dao.entity.StudentEntity;
import com.example.demo.model.dto.StudentAndTeachersDto;
import com.example.demo.model.dto.StudentRequestDto;
import com.example.demo.model.dto.StudentResponseDto;
import com.example.demo.model.dto.TeacherResponseDto;

import java.util.Set;
import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentResponseDto mapEntityToResponseDto(StudentEntity entity) {
        return StudentResponseDto.builder()
                .name(entity.getName())
                .lastname(entity.getLastname())
                .course(entity.getCourse())
                .build();
    }

    public static StudentEntity mapRequestDtoToEntity(StudentRequestDto dto) {
        return StudentEntity.builder()
                .name(dto.getName())
                .lastname(dto.getLastname())
                .age(dto.getAge())
                .course(dto.getCourse())
                .build();
    }

    public static StudentAndTeachersDto mapEntityToStudentAndTeachersDto(StudentEntity studentEntity) {
        Set<TeacherResponseDto> teachers = studentEntity
                .getEnrolledTeacher()
                .stream()
                .map(TeacherMapper::mapEntityToResponseDto)
                .collect(Collectors.toSet());
        return StudentAndTeachersDto.builder()
                .name(studentEntity.getName())
                .teachers(teachers)
                .build();
    }
}
