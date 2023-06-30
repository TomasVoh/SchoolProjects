package com.example.schoolProjects.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 400)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "students_id", nullable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teachers_id", nullable = false)
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjects_id", nullable = false)
    private Subject subject;
}
