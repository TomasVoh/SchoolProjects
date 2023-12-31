package com.example.schoolProjects.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 8)
    private String shortcut;
    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teachers = new ArrayList<>();
    @OneToMany(mappedBy = "subject")
    private List<Project> projects = new ArrayList<>();
}
