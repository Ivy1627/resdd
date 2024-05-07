package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.QuantitySurvey;
import itlize.resourcemanagement.repository.QuantitySurveyRepo;
import itlize.resourcemanagement.service.QuantitySurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantitySurveyImpl implements QuantitySurveyService {
    @Autowired
    private QuantitySurveyRepo qsr;
    @Override
    // need to further handle formula
    public boolean createField(QuantitySurvey qs, String colName, QuantitySurvey.Type type, Project proj){
        if(qsr.existsByColNameAndType(qs.getColName(), qs.getType())) {
            return false;
        }
        qs.setProject(proj);
        qs.setColName(colName);
        qs.setType(type);
        return true;
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
