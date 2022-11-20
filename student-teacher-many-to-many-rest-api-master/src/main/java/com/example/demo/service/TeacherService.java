package com.example.demo.service;


import com.example.demo.dao.entity.StudentEntity;
import com.example.demo.dao.entity.TeacherEntity;
import com.example.demo.dao.repository.StudentRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.dto.TeacherAndStudentsDto;
import com.example.demo.model.dto.TeacherRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.model.constants.ExceptionConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;


    public void createTeacher(TeacherRequestDto dto) {
        log.info("ActionLog.createTeacher.start");

        TeacherEntity teacher = TeacherMapper.mapRequestDtoToEntity(dto);
        teacherRepository.save(teacher);

        log.info("ActionLog.createTeacher.success");
    }


    public TeacherAndStudentsDto getTeacherAndHisStudents(Long id) {
        log.info("ActionLog.getTeacherAndHisStudents.start id: {}", id);

        TeacherEntity teacherEntity = fetchTeacherIfExist(id);
        TeacherAndStudentsDto teacher = TeacherMapper.mapEntityToTeacherAndStudentsDto(teacherEntity);

        log.info("ActionLog.getTeacherAndHisStudents.success id: {}", id);
        return teacher;
    }

    public List<TeacherAndStudentsDto> getTeachersAndTheirStudents() {
        log.info("ActionLog.getTeachersAndTheirStudents.start");

        List<TeacherAndStudentsDto> teachers = teacherRepository.findAll()
                .stream()
                .map(TeacherMapper::mapEntityToTeacherAndStudentsDto)
                .collect(Collectors.toList());

        log.info("ActionLog.getTeachersAndTheirStudents.success");
        return teachers;
    }

    public void enrollStudentToTeacher(Long teacherId, Long studentId) {
        log.info("ActionLog.enrollStudentToTeacher.start teacher id: {}, student id: {}", teacherId, studentId);

        TeacherEntity teacher = fetchTeacherIfExist(teacherId);
        StudentEntity student = fetchStudentIfExist(studentId);
        teacher.enrollStudent(student);
        teacherRepository.save(teacher);

        log.info("ActionLog.enrollStudentToTeacher.success teacher id: {}, student id: {}", teacherId, studentId);

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
