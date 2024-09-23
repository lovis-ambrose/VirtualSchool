package com.lovis.lovis.repositories.parent;

import com.lovis.lovis.entities.guardian.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {
    Optional<Parent> findByParentId(int parentId);
    Optional<Parent> findByEmail(String email);
}
