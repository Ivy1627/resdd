package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.Resource;

import java.util.List;

public interface ResourceService {
    public boolean createResource(Resource res);

    public Resource updateResourceName(Resource res, String resourceName);

    public boolean deleteResource(Resource res);

    public List<Resource> listAll();

    public void addToProject(Project project, List<Resource> resources);

    public void deleteFromProject(Project project, List<Resource> resources);
}
