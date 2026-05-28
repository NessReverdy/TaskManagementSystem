package org.nessrev.taskmanagementsystem.projects.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.taskmanagementsystem.projects.enums.ProjectStatus;
import org.nessrev.taskmanagementsystem.user.entity.User;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProjectResponse{
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;
    @NotBlank
    @Size(min = 2, max = 1000)
    private String description;
    @NotBlank
    private Set<User> authors;
    @NotBlank
    private ProjectStatus status;
}
