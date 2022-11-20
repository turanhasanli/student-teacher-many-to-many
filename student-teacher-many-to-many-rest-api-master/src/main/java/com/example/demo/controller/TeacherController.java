package com.example.demo.controller;

import com.example.demo.model.dto.TeacherAndStudentsDto;
import com.example.demo.model.dto.TeacherRequestDto;
import com.example.demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/teachers")
@RequiredArgsConstructor
public class TeacherController {


    private final TeacherService teacherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeacher(@RequestBody TeacherRequestDto dto){
        teacherService.createTeacher(dto);
    }

    @GetMapping
    public List<TeacherAndStudentsDto> getTeachersAndTheirStudents(){
        return teacherService.getTeachersAndTheirStudents();
    }


    @GetMapping("/{id}")
    public TeacherAndStudentsDto getTeacherAndHisStudents(@PathVariable Long id){
        return teacherService.getTeacherAndHisStudents(id);
    }



    @PutMapping("/{teacherId}/student/{studentId}")
    public void enrollStudentToTeacher(@PathVariable Long teacherId,
                                       @PathVariable Long studentId){
        teacherService.enrollStudentToTeacher(teacherId,studentId);
    }
}
