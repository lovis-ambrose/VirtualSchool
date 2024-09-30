package com.lovis.lovis.entities.student;

import com.lovis.lovis.entities.attendance.Attendance;
import com.lovis.lovis.entities.course.Course;
import com.lovis.lovis.entities.guardian.Parent;
import com.lovis.lovis.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "student number is required")
    @Positive(message = "student number must be positive")
    private int studentNumber;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "registration number is required.")
    private String registrationNumber;
    @NotBlank(message = "first name is required.")
    private String firstName;
    @NotBlank(message = "first name is required.")
    private String lastName;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "email is required.")
    @Email(message = "invalid email address")
    private String email;
    @NotNull(message = "The date of birth is required.")
    @Past(message = "The date of birth must be in the past.")
    private Date dateOfBirth;
    @NotNull(message = "The gender is required.")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotBlank(message = "address is required")
    private String address;
    // internationalize
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "invalid phone number.")
    private String phoneNumber;
    @CreationTimestamp
    private Date dateOfEnrollment;
    @UpdateTimestamp
    private Date dateOfUpdate;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> courses;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Attendance> attendance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
//    @JsonIgnore
    private Parent parent;
}
