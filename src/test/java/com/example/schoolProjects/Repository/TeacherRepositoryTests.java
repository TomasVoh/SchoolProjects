package com.example.schoolProjects.Repository;

import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Model.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TeacherRepositoryTests {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void TeacherRepo_SaveTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("Tomáš Vobořil");
        teacher.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Unknown_person.jpg/1200px");
        Teacher savedTeacher = teacherRepository.save(teacher);
        Assertions.assertNotNull(savedTeacher);
        Assertions.assertTrue(savedTeacher.getId() > 0);
        Assertions.assertEquals("Tomáš Vobořil", savedTeacher.getName());
    }

    @Test
    public void TeacherRepo_ReturnAll() {
        Teacher teacher = new Teacher();
        teacher.setName("Tomáš Vobořil");
        teacher.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Unknown_person.jpg/1200px");
        teacherRepository.save(teacher);
        List<Teacher> teacherList = teacherRepository.findAll();
        Assertions.assertTrue(teacherList.size() > 0);
    }

    @Test
    public void TeacherRepo_UpdateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("Tomáš Vobořil");
        teacher.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Unknown_person.jpg/1200px");
        Teacher savedTeacher = teacherRepository.save(teacher);
        savedTeacher.setName("Libor Přiklopil");
        Teacher updateTeacher = teacherRepository.save(savedTeacher);
        Assertions.assertEquals("Libor Přiklopil", updateTeacher.getName());
    }

    @Test
    public void TeacherRepo_TeacherHasSubjects() {
        Teacher teacher = new Teacher();
        teacher.setName("Tomáš Vobořil");
        teacher.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Unknown_person.jpg/1200px");
        Subject subject = new Subject();
        subject.setName("Programování");
        subject.setShortcut("PG");
        Subject secondSubject = new Subject();
        secondSubject.setName("Webové aplikace");
        secondSubject.setShortcut("WA");
        subjectRepository.save(secondSubject);
        subjectRepository.save(subject);
        List<Subject> subjects = subjectRepository.findAll();
        teacher.setSubjects(subjects);
        Teacher savedTeacher = teacherRepository.save(teacher);
        Assertions.assertEquals(2, savedTeacher.getSubjects().size());
    }
}
