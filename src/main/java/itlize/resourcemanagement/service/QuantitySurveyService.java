package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.QuantitySurvey;

public interface QuantitySurveyService {
    public boolean createField(QuantitySurvey qs, String colName, QuantitySurvey.Type type, Project proj);
//    public QuantitySurvey calculateFormula(QuantitySurvey qs, String formula);
//    public boolean deleteField(String colName);

    //update field will simply be achieved by setters so no need to include it here
}
