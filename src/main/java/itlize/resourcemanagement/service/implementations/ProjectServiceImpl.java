package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo pr;

    @Override
    public boolean createProject(Project proj, User user){
        if (pr.existsByProjName(proj.getProjName())) {
            return false;
        }
        proj.setUser(user);
        pr.save(proj);
        return true;
    }

    @Override
    public Project updateProject(Project proj, String projName) {
        proj.setProjName(projName);
        return pr.save(proj);
    }

    @Override
    public boolean deleteProject(String projName){
        if (!pr.existsByProjName(projName)) {
            return false;
        }
        pr.delete(pr.findByProjName(projName).orElse(null));
        return true;
    }

}
