package org.nessrev.taskmanagementsystem.projects.mapper;

import org.nessrev.taskmanagementsystem.projects.dto.ProjectResponse;
import org.nessrev.taskmanagementsystem.projects.dto.ProjectRequest;
import org.nessrev.taskmanagementsystem.projects.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequest projectRequest) {
        Project project = new Project();
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setStatus(projectRequest.getStatus());
        project.setAuthors(projectRequest.getAuthors());

        return project;
    }

    public ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getAuthors(),
                project.getStatus()
        );
    }

    public void updateRequest(Project project, ProjectRequest projectRequest) {
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setStatus(projectRequest.getStatus());
        project.setAuthors(projectRequest.getAuthors());
    }
}
