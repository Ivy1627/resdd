package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.QuantitySurvey;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.QuantitySurveyRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.QuantitySurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantitySurveyImpl implements QuantitySurveyService {
    @Autowired
    private QuantitySurveyRepo qsr;
    @Autowired
    private ProjectRepo pr;
    @Autowired
    private UserRepo ur;

    @Override
    // need to further handle formula
    public boolean createField(QuantitySurvey qs){
        if(qsr.existsByColNameAndType(qs.getColName(), qs.getType())) {
            return false;
        }
//        qs.setProject(proj);
//        qs.setColName(colName);
//        qs.setType(type);
        qsr.save(qs);
        return true;
    }

    @Override
    public boolean deleteField(String colName, Project project) {
        if(!qsr.existsByColNameAndProj(colName, project)) {
            return false;
        }
        QuantitySurvey qs = qsr.findByColNameAndProj(colName, project);
        qsr.delete(qs);
        return true;
    }

    @Override
    public List<QuantitySurvey> displayTable(String username, String projName) {
        Project proj = pr.findByUser(ur.findByUsername(username)).orElse(null);
        return qsr.findByProj(proj);
    }
//    @Override
//    public QuantitySurvey calculateFormula(QuantitySurvey qs, String formula){
//
//    }
//    @Override
//    public boolean deleteField(String colName){
//        int updatedRows = qsr.updateByColName(null);
//        // Check if any rows were updated
//        return updatedRows > 0;
//    }

}
