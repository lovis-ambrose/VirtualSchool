package com.lovis.lovis.entities.attendance;

import com.lovis.lovis.entities.course.Course;
import com.lovis.lovis.entities.student.Student;
import com.lovis.lovis.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "room number is required.")
    private String roomNumber;
    @NotBlank(message = "attendance date is required.")
    private String attendanceDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String notes;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}
