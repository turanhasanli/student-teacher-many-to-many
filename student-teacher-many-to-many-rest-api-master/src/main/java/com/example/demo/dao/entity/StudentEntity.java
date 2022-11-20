package com.example.demo.dao.entity;

import com.example.demo.model.enums.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Course course;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private Set<TeacherEntity> teachers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "teacher_student_fkeys"
            ,inverseJoinColumns = @JoinColumn(name = "teacher_fk"),
            joinColumns = @JoinColumn(name = "student_fk")
    )
    private Set<TeacherEntity> enrolledTeacher = new HashSet<>();

    public void enrollTeacher(TeacherEntity teacher) {
        enrolledTeacher.add(teacher);
    }
}
