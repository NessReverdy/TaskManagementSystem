package org.nessrev.taskmanagementsystem.projects.repo;

import org.nessrev.taskmanagementsystem.projects.entity.Project;
import org.nessrev.taskmanagementsystem.projects.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByName(String name);

    Optional<Project> getProjectByName(String name);

    List<Project> findByStatus(ProjectStatus status);

    List<Project> findByAuthorsId(Long userId);
}
