package com.lovis.lovis.repositories.student;

import com.lovis.lovis.entities.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);
    Student findByRegistrationNumber(String registrationNumber);
}
