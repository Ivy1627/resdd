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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Project>> listAll(@PathVariable Long userId){
        User user = ur.findById(userId).orElse(null);
        if(user!=null){
            List<Project> projects = ps.listAll(user);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/display/{userId}/{projId}")
    public ResponseEntity<Project> displayProject(@PathVariable Long userId, @PathVariable Long projId){
        User user = ur.findById(userId).orElse(null);
        if(user!=null){
            Project proj = ps.displayProject(user, projId);
            return new ResponseEntity<>(proj, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create/{username}/{projName}")
    public ResponseEntity<Project> createProject(@PathVariable String username, @PathVariable String projName){
        User user = ur.findByUsername(username);
        Project project = new Project();
        project.setUser(user);
        project.setProjName(projName);
        System.out.println(username);
        boolean success = ps.createProject(project);
        if(success){
            return new ResponseEntity<Project>(project, HttpStatus.OK);
        }
        return new ResponseEntity<Project>(project, HttpStatus.CONFLICT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{username}/{proj_name}")
    public ResponseEntity<String> delete(@PathVariable String username, @PathVariable String proj_name){
        Project proj = pr.findByProjName(proj_name).orElse(null);
        boolean success = ps.deleteProject(proj);
        if(success){
            return new ResponseEntity<String>("Projected deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Project to be deleted doesn't exist", HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = pr.findById(id).orElse(null);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
