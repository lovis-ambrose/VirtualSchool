package com.lovis.lovis.entities.attendance;

import com.lovis.lovis.entities.course.Course;
import com.lovis.lovis.entities.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId;
    private String roomNumber;
    private String attendanceDate;
    private String status;
    private String notes;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}
