package com.lovis.lovis.entities.classroom;

import com.lovis.lovis.entities.course.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "room number is required.")
    private String classroomNumber;
    @NotNull(message = "capacity is required.")
    private int capacity;
    @OneToMany
    private List<Course> courses;
}
