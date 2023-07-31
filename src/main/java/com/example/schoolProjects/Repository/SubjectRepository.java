package com.example.schoolProjects.Repository;

import com.example.schoolProjects.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByShortcut(String shortcut);
}
