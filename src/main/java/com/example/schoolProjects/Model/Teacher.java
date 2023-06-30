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
    private String photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Unknown_person.jpg/1200px-Unknown_person.jpg";
    @OneToMany(mappedBy = "teacher")
    private List<Project> projects = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "teachers_subjects",
            joinColumns = {@JoinColumn(name = "teachers_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "subjects_id", referencedColumnName = "id")}
    )
    private List<Subject> subjects = new ArrayList<>();
}
