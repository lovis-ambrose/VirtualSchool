package com.lovis.lovis.entities.guardian;

import com.lovis.lovis.entities.student.Student;
import jakarta.persistence.*;
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
    private String parentName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private String address;
    @OneToMany
    private List<Student> students;
}
