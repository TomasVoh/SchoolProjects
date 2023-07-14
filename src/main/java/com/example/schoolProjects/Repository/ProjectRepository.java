package com.example.schoolProjects.Repository;

import com.example.schoolProjects.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectByStudentId(long id);
    List<Project> findProjectByTeacherId(long id);
}
