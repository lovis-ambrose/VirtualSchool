package com.lovis.lovis.controllers.teacher;

import com.lovis.lovis.entities.teacher.Teacher;
import com.lovis.lovis.services.teacher.TeacherService;
import com.lovis.lovis.services.teacher.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school/v1/teachers/")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("all")
    public List<Teacher> getAll() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("details/{teacherId}")
    public Optional<Teacher> getTeacherByEmail(@PathVariable int teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @PostMapping("register")
    public ResponseEntity<Teacher> registerTeacher(@RequestBody TeacherDTO teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("update/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody TeacherDTO teacher, @PathVariable int teacherId) {
        return teacherService.updateTeacher(teacher, teacherId);
    }

    @DeleteMapping("delete/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable int teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }
}
