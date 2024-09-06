package itlize.resourcemanagement.controller;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.exception.ResourceNotFoundException;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.ResourceRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<Resource>> listAll(){
        List<Resource> resources = rs.listAll();
        if(resources!=null){
            return new ResponseEntity<>(resources, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all/{userId}/{projId}")
    public ResponseEntity<List<Resource>> listFromProject(@PathVariable Long userId, @PathVariable Long projId) {
        User user = ur.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        Project project = pr.findByIdAndUser(projId, user).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projId + " for user: " + userId));

        List<Resource> resources = rr.findResourcesByUserAndProject(userId, projId);
        return new ResponseEntity<>(resources, HttpStatus.OK);
//        User user = ur.findById(userId).orElse(null);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Project project = pr.findByIdAndUser(projId, user).orElse(null);
//        if (project == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        List<Resource> resources = rr.findResourcesByUserAndProject(userId, projId);
//        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<Resource> add(@RequestBody Resource res){
        boolean success = rs.createResource(res);
        if(success){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addTo/{userId}/{projId}")
    public ResponseEntity<List<Resource>> addTo(@PathVariable Long userId, @PathVariable Long projId, @RequestBody List<Resource> resources){
//        System.out.println(STR."Received userId: \{userId}");
//        System.out.println(STR."Received projId: \{projId}");
//        System.out.println(STR."Received resources: \{resources}");

        User user = ur.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Project project = pr.findByIdAndUser(projId, user).orElse(null);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        rs.addToProject(project, resources);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteFrom/{userId}/{projId}")
    public ResponseEntity<List<Resource>> deleteFrom(@PathVariable Long userId, @PathVariable Long projId, @RequestBody List<Resource> resources){
        User user = ur.findById(userId).orElse(null);
        assert user != null;
        Project project = pr.findByIdAndUser(projId, user).orElse(null);
        if(project==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        rs.deleteFromProject(project, resources);
        return new ResponseEntity<>(resources, HttpStatus.OK);
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

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Resource res){
        boolean success = rs.deleteResource(res);
        if(success){
            return new ResponseEntity<>("resource deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource does not exist", HttpStatus.NOT_FOUND);
    }
}
