package org.nessrev.taskmanagementsystem.projects.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Size(max = 1000, message = "Description can't be longer than 1000 characters")
    private String description;

    private List<String> author;

}
