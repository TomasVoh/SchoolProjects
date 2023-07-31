package com.example.schoolProjects.Service;

import com.example.schoolProjects.Model.Project;
import com.example.schoolProjects.Model.Student;
import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Model.Teacher;
import com.example.schoolProjects.Repository.StudentRepository;
import com.example.schoolProjects.Repository.SubjectRepository;
import com.example.schoolProjects.Repository.TeacherRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelImportService {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;

    public ExcelImportService(StudentRepository studentRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    public boolean isFileValidXlsx(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public List<Subject> getSubjectData(InputStream inputStream) {
        List<Subject> subjects = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Předměty");
            int rowIndex = 0;
            for(Row row : sheet) {
                if(rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Subject subject = new Subject();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> {
                            subject.setName(cell.getStringCellValue());
                        }
                        case 1 -> {
                            subject.setShortcut(cell.getStringCellValue());
                        }
                        default -> {

                        }
                    }
                    cellIndex++;
                }
                subjects.add(subject);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }

    public List<Project> getProjectData(InputStream inputStream) {
        List<Project> projects = new ArrayList<>();
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheet("Projekty");
            int rowIndex = 0;
            for(Row row : xssfSheet) {
                if(rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                int cellIndex = 0;
                Iterator<Cell> cellIterator = row.iterator();
                Project project = new Project();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> {
                            project.setName(cell.getStringCellValue());
                        }
                        case 1 -> {
                            String studentName = cell.getStringCellValue();
                            Student student = studentRepository.findByName(studentName);
                            project.setStudent(student);
                        }
                        case 2 -> {
                            String teacherName = cell.getStringCellValue();
                            Teacher teacher = teacherRepository.findByName(teacherName);
                            project.setTeacher(teacher);
                        }
                        case 3 -> {
                            String shortcut = cell.getStringCellValue();
                            Subject subject = subjectRepository.findByShortcut(shortcut);
                            project.setSubject(subject);
                        }
                        default -> {

                        }
                    }
                    cellIndex++;
                }
                projects.add(project);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }
}
