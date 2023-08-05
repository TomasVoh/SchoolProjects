package com.example.schoolProjects.Repository;

import com.example.schoolProjects.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectByStudentId(long id);
    List<Project> findProjectByTeacherId(long id);

    @Query("SELECT p FROM Project p WHERE p.name LIKE CONCAT('%', :name, '%') ")
    List<Project> findProjectsBySearch(@Param("name") String name);

    void deleteById(long id);
}
