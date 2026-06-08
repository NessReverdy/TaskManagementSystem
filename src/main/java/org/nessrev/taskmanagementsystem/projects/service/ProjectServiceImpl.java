package org.nessrev.taskmanagementsystem.projects.service;

import lombok.AllArgsConstructor;
import org.nessrev.taskmanagementsystem.exception.custom.ProjectNotFoundException;
import org.nessrev.taskmanagementsystem.projects.dto.ProjectRequest;
import org.nessrev.taskmanagementsystem.projects.entity.Project;
import org.nessrev.taskmanagementsystem.projects.enums.ProjectStatus;
import org.nessrev.taskmanagementsystem.projects.mapper.ProjectMapper;
import org.nessrev.taskmanagementsystem.projects.repo.ProjectRepository;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
  private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;

  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  public Project getProjectById(Long id) {
    return projectRepository.findById(id)
      .orElseThrow(() -> {
        log.error("Project with id {} not found", id);
        return new ProjectNotFoundException(id);
      });
  }

  public Project getProjectByName(String name) {
    return projectRepository.getProjectByName(name)
      .orElseThrow(() -> {
        log.error("Project with name {} not found", name);
        return new ProjectNotFoundException(name);
      });
  }

  public Project createProject(ProjectRequest projectRequest) {
    Project project = new Project();

    checkProjectNameExists(projectRequest.getName());

    project.setName(projectRequest.getName());
    project.setDescription(projectRequest.getDescription());
    project.setAuthors(projectRequest.getAuthors());
    project.setStatus(projectRequest.getStatus());

    return projectRepository.save(project);
  }

  public Project updateProject(Long id, ProjectRequest projectRequest) {
    Project project = getProjectById(id);

    project.setName(projectRequest.getName());
    project.setDescription(projectRequest.getDescription());
    project.setAuthors(projectRequest.getAuthors());
    project.setStatus(projectRequest.getStatus());

    return projectRepository.save(project);
  }

  public void deleteProjectById(Long id) {
    Project project = getProjectById(id);
    projectRepository.delete(project);
  }

  public Project addAuthor(Long id, User user) {
    Project project = getProjectById(id);
    project.getAuthors().add(user);

    return projectRepository.save(project);
  }

  public Project removeAuthor(Long id, User user) {
    Project project = getProjectById(id);
    project.getAuthors().remove(user);

    return projectRepository.save(project);
  }

  public Project updateStatus(Long id, ProjectStatus status) {
    Project project = getProjectById(id);
    project.setStatus(status);

    return projectRepository.save(project);
  }

  public List<Project> getProjectsByStatus(ProjectStatus status) {
    return projectRepository.findByStatus(status);
  }

  public List<Project> getProjectsByAuthor(Long userId) {
    return projectRepository.findByAuthorsId(userId);
  }

  private void checkProjectNameExists(String name) {
    if (projectRepository.existsByName(name)) {
      throw new IllegalArgumentException("Project name already exists");
    }
  }
}
