package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.Resource;

public interface ResourceService {
    public boolean createResource(Resource res);

    public Resource updateResourceName(Resource res, String resourceName);

    public boolean deleteResource(Resource res);
}
