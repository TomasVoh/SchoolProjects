package com.example.schoolProjects.Repository;

import com.example.schoolProjects.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findTeachersBySubjectsId(long id);
}
