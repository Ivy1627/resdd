package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo pr;
    @Autowired
    private UserRepo ur;

    @Override
    public boolean createProject(Project proj){
//        System.out.println(proj.getProjName());
//        System.out.println(proj.getUser());
        // if project with the same name already exists
        if (pr.existsByProjNameAndUser(proj.getProjName(), proj.getUser())) {
            return false;
        }
        pr.save(proj);
        return true;
    }

    @Override
    public Project updateProject(Project proj, String projName) {
        proj.setProjName(projName);
        return pr.save(proj);
    }

    @Override
    public boolean deleteProject(Project project){
        if (!pr.existsByProjNameAndUser(project.getProjName(), project.getUser())) {
            return false;
        }
        pr.delete(project);
        return true;
    }

    // list the projects owned by the current user
    @Override
    public List<Project> listAll(User user) {
        List<Project> res = new ArrayList<>();
        return pr.findAllByUser(user);
    }

    @Override
    public Project displayProject(User user, String projName) {
        return pr.findByProjNameAndUser(projName, user).orElse(null);
    }


}
