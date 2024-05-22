package itlize.resourcemanagement.controller;

import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.service.ResourceAttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ra")
public class ResourceAttributesController {
    @Autowired
    private ResourceAttributesService ras;

    @PostMapping("/create/{colName}")
    public ResponseEntity<String> createColName(@PathVariable String colName){
        boolean success = ras.createColName(colName);
        if(success){
            return new ResponseEntity<String>("new attribute column created", HttpStatus.OK);
        }
        return new ResponseEntity<String>("attribute already exists", HttpStatus.CONFLICT);
    }

    @PostMapping("/create/{colName}/{colVal}")
    public ResponseEntity<String> createColVal(@RequestBody Resource res, @PathVariable String colName, @PathVariable String colVal){
        boolean success = ras.createColVal(res.getResAttributes().getFirst(), res, colName, colVal);
        if(success){
            return new ResponseEntity<String>("colVal created", HttpStatus.OK);
        }
        return new ResponseEntity<String>("colVal already exists", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete/{colName}")
    public ResponseEntity<String> deleteCol(@PathVariable String colName){
        boolean success = ras.deleteColName(colName);
        if(success){
            return new ResponseEntity<String>("attribute deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("attribute to delete does not exist", HttpStatus.CONFLICT);
    }

}
