package com.example.demo.service;


import com.example.demo.dao.entity.StudentEntity;
import com.example.demo.dao.entity.TeacherEntity;
import com.example.demo.dao.repository.StudentRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.StudentAndTeachersDto;
import com.example.demo.model.dto.StudentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.mapper.StudentMapper.*;
import static com.example.demo.model.constants.ExceptionConstants.NOT_FOUND_EXCEPTION_CODE;
import static com.example.demo.model.constants.ExceptionConstants.NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;



    public void createStudent(StudentRequestDto dto) {
        log.info("ActionLog.createStudent.start");

        studentRepository.save(mapRequestDtoToEntity(dto));

        log.info("ActionLog.createStudent.success");
    }


    public void enrollTeacherToStudent(Long studentId, Long teacherId) {
        log.info("ActionLog.enrollTeacherToStudent.start student id: {}, teacher id: {}", studentId, teacherId);

        StudentEntity student = fetchStudentIfExist(studentId);
        TeacherEntity teacher = fetchTeacherIfExist(teacherId);
        student.enrollTeacher(teacher);
        studentRepository.save(student);

        log.info("ActionLog.enrollTeacherToStudent.success student id: {}, teacher id: {}", studentId, teacherId);
    }

    public List<StudentAndTeachersDto> getStudents() {
        log.info("ActionLog.getStudents.start");

        var students = studentRepository
                .findAll()
                .stream()
                .map(studentEntity -> mapEntityToStudentAndTeachersDto(studentEntity))
                .collect(Collectors.toList());

        log.info("ActionLog.getStudents.success");
        return students;
    }

    private TeacherEntity fetchTeacherIfExist(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.fetchTeacherIfExist.error id: {}", id);

            throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE, "Teacher", id), NOT_FOUND_EXCEPTION_CODE);
        });
    }

    private StudentEntity fetchStudentIfExist(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> {
                    log.error("ActionLog.fetchStudentIfExist.error id: {}", id);

                    throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE, "Student", id), NOT_FOUND_EXCEPTION_CODE);
                }
        );
    }
}
