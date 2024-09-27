package com.lovis.lovis.services.parent;

import com.lovis.lovis.entities.guardian.Parent;
import com.lovis.lovis.entities.student.Student;
import com.lovis.lovis.exceptions.response.ApiRequestResponse;
import com.lovis.lovis.exceptions.response.NotFoundResponse;
import com.lovis.lovis.repositories.parent.ParentRepository;
import com.lovis.lovis.repositories.student.StudentRepository;
import com.lovis.lovis.services.parent.dto.ParentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    public Optional<Parent> getParent(@RequestParam int parentId) throws NotFoundResponse {
        Optional<Parent> parent = parentRepository.findByParentId(parentId);
        if (parent.isPresent()) {
            return parentRepository.findByParentId(parentId);
        }
        else {
            throw new NotFoundResponse("Requested parent not found");
        }
    }

    public ResponseEntity<String> addParent(@RequestBody ParentRequest parentRequest) {
        String email = parentRequest.email();
        String reason = "Email already exists";
        if (parentRepository.findByEmail(email).isPresent()) {
            throw new ApiRequestResponse(reason);
        }
        else {
            Student student = studentRepository.findById(parentRequest.studentId()).orElseThrow(() -> new ApiRequestResponse("Student not found"));
            //create the new entity
            Parent parent = new Parent();
            parent.setParentName(parentRequest.parentName());
            parent.setEmail(parentRequest.email());
            parent.setPhoneNumber(parentRequest.phoneNumber());
            parent.setAddress(parentRequest.address());
            // set the parent to a student
            student.setParent(parent);

            // save the parent
            parentRepository.save(parent);
            studentRepository.save(student);
            return ResponseEntity.ok().body("Successfully added parent");
        }
    }

    public ResponseEntity<String> updateParent(@RequestBody Parent parent, @RequestParam int parentId) throws NotFoundResponse {
        Optional<Parent> existingParent = parentRepository.findByParentId(parentId);
        if (existingParent.isPresent()) {
            Parent updatedParent = existingParent.get();
            updatedParent.setParentName(parent.getParentName());
            updatedParent.setEmail(parent.getEmail());
            updatedParent.setAddress(parent.getAddress());
            updatedParent.setStudents(parent.getStudents());
            updatedParent.setPhoneNumber(parent.getPhoneNumber());
            updatedParent.setAddress(parent.getAddress());
            parentRepository.save(updatedParent);
            return ResponseEntity.ok("Successfully updated parent");
        }
        else {
            throw new NotFoundResponse("Requested parent not found");
        }
    }

    public ResponseEntity<String> deleteParent(@RequestParam int parentId) throws NotFoundResponse {
        Optional<Parent> existingParent = parentRepository.findByParentId(parentId);
        if (existingParent.isPresent()) {
            parentRepository.delete(existingParent.get());
        }
        else {
            throw new NotFoundResponse("Requested parent not found");
        }
        return ResponseEntity.ok("Successfully deleted parent");
    }
}
