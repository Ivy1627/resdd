package itlize.resourcemanagement;

import itlize.resourcemanagement.entity.QuantitySurvey;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.QuantitySurveyRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.QuantitySurveyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuantitySurveyTests {
    @Autowired
    private QuantitySurveyRepo qsr;
    @Autowired
    private QuantitySurveyService qss;
    @Autowired
    private ProjectRepo pr;
    @Autowired
    private UserRepo ur;
    @Test
    void testCreateField(){
        QuantitySurvey qs = new QuantitySurvey();
        qs.setType(QuantitySurvey.Type.NUMBER); // Set the type
        qs.setColName("Test Column"); // Set the column name
        qs.setFormula("2 * value"); // Set the formula
        // Set other properties as needed

        // Mock a Project object (you may need to use a mock framework like Mockito)
        User user = ur.findByUsername("Ivy");
        qs.setProj(pr.findByProjNameAndUser("Project 2", user).orElse(null)); // Set the project

        // Call the createField method
        boolean created = qss.createField(qs);

        // Assert that the field was created successfully
        assertTrue(created, "Field creation should succeed");
    }

}
