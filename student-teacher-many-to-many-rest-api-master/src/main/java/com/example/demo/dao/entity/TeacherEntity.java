package com.example.demo.dao.entity;

import com.example.demo.model.enums.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @ManyToMany
    @JoinTable(
            name = "teacher_student_fkeys",
            joinColumns = @JoinColumn(name = "teacher_fk")
            , inverseJoinColumns = @JoinColumn(name = "student_fk"))
    private Set<StudentEntity> students = new HashSet<>();


    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledTeacher")
    private Set<StudentEntity> studentEntities = new HashSet<>();

    public void enrollStudent(StudentEntity student) {
        students.add(student);
    }
}
