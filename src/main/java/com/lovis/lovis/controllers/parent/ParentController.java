package com.lovis.lovis.controllers.parent;

import com.lovis.lovis.entities.guardian.Parent;
import com.lovis.lovis.exceptions.response.NotFoundResponse;
import com.lovis.lovis.services.parent.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/school/v1/parents/")
public class ParentController {
    private final ParentService parentService;

    @GetMapping("all")
    public List<Parent> getAllParents() {
        return parentService.getAllParents();
    }
    @GetMapping({"parentId"})
    public Optional<Parent> getParentById(@RequestParam int parentId) throws NotFoundResponse {
        return parentService.getParent(parentId);
    }

    @PostMapping("addNew")
    public ResponseEntity<String> addNewParent(@RequestBody Parent parent) {
        return parentService.addParent(parent);
    }
    @PutMapping("update/{parentId}")
    public ResponseEntity<String> updateParent(@RequestBody Parent parent, @PathVariable int parentId) throws NotFoundResponse {
        return parentService.updateParent(parent, parentId);
    }

    @DeleteMapping("delete/{parentId}")
    public ResponseEntity<String> deleteParent(@PathVariable int parentId) throws NotFoundResponse {
        return parentService.deleteParent(parentId);
    }
}
