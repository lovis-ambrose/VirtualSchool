package com.lovis.lovis.entities.course;

import com.lovis.lovis.entities.attendance.Attendance;
import com.lovis.lovis.entities.classroom.Classroom;
import com.lovis.lovis.entities.student.Student;
import com.lovis.lovis.entities.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    private String courseDescription;
    private int credits;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> student;
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;
    @ManyToOne
    private Classroom classroom;
    @OneToMany
    private List<Attendance> attendances;
}
