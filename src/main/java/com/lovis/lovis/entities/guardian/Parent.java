package com.lovis.lovis.entities.guardian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lovis.lovis.entities.student.Student;
import com.lovis.lovis.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parentId;
    @NotBlank(message = "full name is required.")
    private String parentName;
    @NotNull(message = "The gender is required.")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "email is required.")
    @Email(message = "Invalid email address")
    private String email;
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "invalid phone number.")
    private String phoneNumber;
    @NotBlank(message = "address is required.")
    private String address;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> students;
}
