package com.lovis.lovis.entities.classroom;

import com.lovis.lovis.entities.course.Course;
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
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classroomId;
    private String classroomNumber;
    private int capacity;
    @OneToMany
    private List<Course> courses;
}
