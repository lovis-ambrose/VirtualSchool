package com.lovis.lovis.entities.teacher;

import com.lovis.lovis.entities.course.Course;
import com.lovis.lovis.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    @NotBlank(message = "first name is required.")
    private String firstName;
    @NotBlank(message = "last name is required.")
    private String lastName;
    @NotNull(message = "The gender is required.")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "email is required.")
    @Email(message = "Invalid email address")
    private String email;
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "invalid phone number.")
    private String phoneNumber;
    @CreationTimestamp
    private Date hireDate;
    @OneToMany
    private List<Course> courses;
}
