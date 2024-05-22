package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.QuantitySurvey;

import java.util.List;

public interface QuantitySurveyService {
    public boolean createField(QuantitySurvey qs);
//    public QuantitySurvey calculateFormula(QuantitySurvey qs, String formula);
    public boolean deleteField(String colName, Project project);

    public List<QuantitySurvey> displayTable(String username, String projName);

    //update field will simply be achieved by setters so no need to include it here
}
