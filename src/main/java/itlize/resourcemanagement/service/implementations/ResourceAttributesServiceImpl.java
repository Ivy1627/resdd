package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.ResourceAttributes;
import itlize.resourcemanagement.repository.ResourceAttributesRepo;
import itlize.resourcemanagement.service.ResourceAttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceAttributesServiceImpl implements ResourceAttributesService {
    @Autowired
    private ResourceAttributesRepo rap;

//    @Override
//    public boolean createColName(String colName){
//
//    }

    @Override
    public boolean createColVal(ResourceAttributes resAttr, Resource res, String colName, String colVal){
        // Q: if not found, add the column in the front end?? anything to do in the backend?
        if(rap.existsByColNameAndColVal(resAttr.getColName(), resAttr.getColVal())) {
            return false;
        }
        resAttr.setRes(res);
        resAttr.setColName(colName);
        resAttr.setColVal(colVal);
        rap.save(resAttr);
        return true;
    }

    @Override
    public ResourceAttributes updateResourceAttributes(ResourceAttributes resAttr, String colVal){
        resAttr.setColVal(colVal);
        return rap.save(resAttr);
    }

    @Override
    public boolean deleteColName(ResourceAttributes resAttr, String colName){
        // Find all ResourceAttributes entries with the specified colName
        List<ResourceAttributes> entriesToDelete = rap.findAllByColName(colName);
        // Check if entries with the specified colName exist
        if (!entriesToDelete.isEmpty()) {
            rap.deleteAll(entriesToDelete);
            return true;
        }
        //if no entries to delete, return false;
        return false;
    }
}
