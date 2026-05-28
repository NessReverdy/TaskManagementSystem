package org.nessrev.taskmanagementsystem.exception.custom;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super ("Project with id " + id + " not found");
    }

    public ProjectNotFoundException(String projectName) {
        super ("Project with name " + projectName + " not found");
    }

}
