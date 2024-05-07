package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.User;

public interface ProjectService {
    public boolean createProject(Project proj, User user);

    public Project updateProject(Project proj, String projName);

    public boolean deleteProject(String projName);
}
