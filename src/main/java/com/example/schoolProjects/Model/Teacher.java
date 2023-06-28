package com.example.schoolProjects.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 8)
    private String prefix;
    @Column(length = 8)
    private String suffix;
    @Column(nullable = false, length = 500)
    private String photoUrl;
    @OneToMany(mappedBy = "teacher")
    private List<Project> projects = new ArrayList<>();
    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects = new ArrayList<>();
}
