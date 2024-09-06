package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.ResourceRepo;
import itlize.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceRepo rr;
    @Autowired
    private ProjectRepo pr;

    @Override
    public boolean createResource(Resource res){
        if (rr.existsByResName(res.getResName())) {
            return false;
        }
        rr.save(res);
        return true;
    }

    @Override
    public Resource updateResourceName(Resource res, String resourceName){
        res.setResName(resourceName);
        rr.save(res);
        return res;
    }

    @Override
    public boolean deleteResource(Resource res){
        if (!rr.existsByResNameOrResCode(res.getResName(), res.getResCode())) {
            return false;
        }
        rr.delete(res);
        return true;
    }

    @Override
    public List<Resource> listAll() {
        return rr.findAllWithAttr();
    }

    @Override
    public void addToProject(Project project, List<Resource> resources) {
        for (Resource resource : resources) {
            resource = rr.findById(resource.getId()).orElse(null);
            project = pr.findById(project.getId()).orElse(null);
            assert project != null;
            project.getResources().add(resource);
            assert resource != null;
            rr.save(resource);
            resource.getProjects().add(project);
            pr.save(project);
        }
//        pr.save(project);
//        rr.saveAll(resources);
        System.out.println(Arrays.toString(project.getResources().toArray()));
    }

    @Override
    public void deleteFromProject(Project project, List<Resource> resources) {
        System.out.println("debug deleteFromProject");
        for (Resource resource : resources) {
            resource = rr.findById(resource.getId()).orElse(null);
            project = pr.findById(project.getId()).orElse(null);
            assert project != null;
            project.getResources().remove(resource);
            assert resource != null;
            System.out.println(resource.getId());
            rr.save(resource);
            resource.getProjects().remove(project);
            pr.save(project);
        }
    }
}
