package com.example.schoolProjects.Service;

import com.example.schoolProjects.Dto.StudentPage;
import com.example.schoolProjects.Model.Student;
import com.example.schoolProjects.Repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public StudentPage getAllByPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Student> pageableStudents = studentRepository.findAll(pageable);
        List<Student> students = pageableStudents.getContent();
        StudentPage studentPage = new StudentPage();
        studentPage.setContent(pageableStudents.getContent());
        studentPage.setPageNum(pageableStudents.getNumber());
        studentPage.setPageSize(pageableStudents.getSize());
        studentPage.setAllElements(pageableStudents.getTotalElements());
        studentPage.setAllPages(pageableStudents.getTotalPages());
        studentPage.setLast(pageableStudents.isLast());
        return studentPage;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
}
