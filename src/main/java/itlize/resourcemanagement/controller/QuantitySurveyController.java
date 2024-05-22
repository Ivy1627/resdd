package itlize.resourcemanagement.controller;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.QuantitySurvey;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.QuantitySurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.locks.LockSupport;

@RestController
@RequestMapping("/qs")
public class QuantitySurveyController {
    @Autowired
    private QuantitySurveyService qss;
    @Autowired
    private UserRepo ur;
    @Autowired
    private ProjectRepo pr;

    @PostMapping("/create/{username}/{projName}")
    public ResponseEntity<String> createField(@PathVariable String username, @PathVariable String projName, @RequestBody QuantitySurvey qs){
        User user = ur.findByUsername(username);
        Project project = pr.findByProjNameAndUser(projName, user).orElse(null);
        qs.setProj(project);

//        qs.setColName(qs.getColName());
//        qs.setType(qs.getType());
        boolean success = qss.createField(qs);
        if(success){
            return new ResponseEntity<String>("New field created", HttpStatus.OK);
        }
        return new ResponseEntity<String>("field exists", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete/{username}/{projName}/{fieldName}")
    public ResponseEntity<String> deleteField(@PathVariable String username, @PathVariable String projName, @PathVariable String fieldName){
        User user = ur.findByUsername(username);
        Project project = pr.findByProjNameAndUser(projName, user).orElse(null);
        boolean success = qss.deleteField(fieldName, project);
//        qs.setColName(qs.getColName());
//        qs.setType(qs.getType());
        if(success){
            return new ResponseEntity<String>("New field created", HttpStatus.OK);
        }
        return new ResponseEntity<String>("field exists", HttpStatus.CONFLICT);
    }

    @GetMapping("/display/{username}/{projName}")
    public List<QuantitySurvey> displayTable(@PathVariable String username, @PathVariable String projName){
        return (qss.displayTable(username, projName));
    }
}
