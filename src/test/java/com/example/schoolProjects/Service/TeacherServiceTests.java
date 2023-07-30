package com.example.schoolProjects.Service;

import com.example.schoolProjects.Model.Teacher;
import com.example.schoolProjects.Repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class TeacherServiceTests {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @Test
    public void TeacherService_CreateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("Tomáš Vobořil");
        teacher.setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Unknown_person.jpg/1200px");
        Mockito.when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacher);
        Teacher savedTeacher = teacherService.createTeacher(teacher);
        Assertions.assertNotNull(savedTeacher);
    }
}
