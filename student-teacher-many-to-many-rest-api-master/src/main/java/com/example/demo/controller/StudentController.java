package com.example.demo.controller;


import com.example.demo.model.dto.StudentAndTeachersDto;
import com.example.demo.model.dto.StudentRequestDto;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody StudentRequestDto dto) {
        service.createStudent(dto);
    }


    @PutMapping("{studentId}/teachers/{teacherId}")
    public void enrollTeacherToStudent(@PathVariable Long studentId,
                                       @PathVariable Long teacherId) {
        service.enrollTeacherToStudent(studentId, teacherId);
    }

    @GetMapping
    public List<StudentAndTeachersDto> getStudents() {
        return service.getStudents();
    }
}
