package itlize.resourcemanagement.controller;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.ProjectService;
import itlize.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService ps;
    @Autowired
    private UserRepo ur;
    @Autowired
    private UserService us;
    @Autowired
    private ProjectRepo pr;

    @GetMapping("/all/{username}")
    public ResponseEntity<List<Project>> listAll(@PathVariable String username){
        List<Project> projects = ps.listAll(ur.findByUsername(username));
        if(projects!=null){
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/display/{username}/{projName}")
    public ResponseEntity<Project> displayProject(@PathVariable String username, @PathVariable String projName){
        User user = ur.findByUsername(username);
        Project proj = ps.displayProject(user, projName);
        if(proj!=null){
            return new ResponseEntity<>(proj, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create/{username}/{projName}")
    public ResponseEntity<String> createProject(@PathVariable String username, @PathVariable String projName){
        User user = ur.findByUsername(username);
        Project project = new Project();
        project.setUser(user);
        project.setProjName(projName);
        System.out.println(username);
        boolean success = ps.createProject(project);
        if(success){
            return new ResponseEntity<String>("New projected created", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Project exists", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete/{username}/{proj_name}")
    public ResponseEntity<String> delete(@PathVariable String username, @PathVariable String proj_name){
        Project proj = pr.findByProjName(proj_name).orElse(null);
        boolean success = ps.deleteProject(proj);
        if(success){
            return new ResponseEntity<String>("Projected deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Project to be deleted doesn't exist", HttpStatus.NOT_FOUND);
    }
}
