package com.lovis.lovis.entities.student;

import com.lovis.lovis.entities.attendance.Attendance;
import com.lovis.lovis.entities.course.Course;
import com.lovis.lovis.entities.guardian.Parent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    @Column(unique = true, nullable = false)
    private Long studentNumber;
    @Column(unique = true, nullable = false)
    private String registrationNumber;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private Date dateOfBirth;
    private String gender;
    @CreationTimestamp
    private Date dateOfEnrollment;
    @UpdateTimestamp
    private Date dateOfUpdate;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> courses;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Attendance> attendance;
    @ManyToOne(fetch = FetchType.EAGER)
    private Parent parent;
}
