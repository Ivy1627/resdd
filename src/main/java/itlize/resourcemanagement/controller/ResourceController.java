package itlize.resourcemanagement.controller;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.ResourceRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceRepo rr;
    @Autowired
    private ResourceService rs;
    @Autowired
    private ProjectRepo pr;
    @Autowired
    private UserRepo ur;

    @GetMapping("/all")
    public ResponseEntity<List<Resource>> listAll(){
        List<Resource> resources = rs.listAll();
        if(resources!=null){
            return new ResponseEntity<>(resources, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Resource res){
        boolean success = rs.createResource(res);
        if(success){
            return new ResponseEntity<String>("New resource created", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Resource exists", HttpStatus.CONFLICT);
    }

    @PostMapping("/addTo/{username}/{projName}")
    public ResponseEntity<String> addTo(@PathVariable String username, @PathVariable String projName, @RequestBody List<Resource> resources){
        User user = ur.findByUsername(username);
        Project project = pr.findByProjNameAndUser(projName, user).orElse(null);
        if(project==null){
            return new ResponseEntity<String>("Project does not exist", HttpStatus.NOT_FOUND);
        }
        rs.addToProject(project, resources);
        return new ResponseEntity<String>("Resource added to project", HttpStatus.OK);
    }

    @PostMapping("/deleteFrom/{username}/{projName}")
    public ResponseEntity<String> deleteFrom(@PathVariable String username, @PathVariable String projName, @RequestBody List<Resource> resources){
        Project project = pr.findByProjNameAndUser(projName, ur.findByUsername(username)).orElse(null);
        if(project==null){
            return new ResponseEntity<String>("Project does not exist", HttpStatus.NOT_FOUND);
        }
        rs.deleteFromProject(project, resources);
        return new ResponseEntity<String>("Resource deleted from the project", HttpStatus.OK);
    }

    // this should be in resourceAttributes
//    @PatchMapping("/update")
//    public ResponseEntity<String> update(@RequestBody Resource res){
//        boolean success = rs.createResource(res);
//        if(success){
//            return new ResponseEntity<String>("New resource created", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("Resource exists", HttpStatus.CONFLICT);
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Resource res){
        boolean success = rs.deleteResource(res);
        if(success){
            return new ResponseEntity<String>("resource deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Resource does not exist", HttpStatus.NOT_FOUND);
    }
}
