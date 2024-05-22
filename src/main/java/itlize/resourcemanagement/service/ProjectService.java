package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.User;

import java.util.List;

public interface ProjectService {
    public boolean createProject(Project project);

    public Project updateProject(Project proj, String projName);

    public boolean deleteProject(Project project);

    public List<Project> listAll(User user);

    public Project displayProject(User user, String projName);
}
