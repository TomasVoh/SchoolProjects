package com.example.schoolProjects.Dto;

import com.example.schoolProjects.Model.Student;
import java.util.*;
import lombok.Data;

@Data
public class StudentPage {
    private List<Student> content;
    private int pageNum;
    private int pageSize;
    private long allElements;
    private int allPages;
    private boolean isLast;
}
