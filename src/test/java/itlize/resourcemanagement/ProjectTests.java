package itlize.resourcemanagement;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProjectTests {
    @Autowired
    private ProjectRepo pr;
    @Autowired
    private UserRepo ur;
    @Autowired
    private ProjectService ps;

    @Test
    void testCreateProject(){
        Project newProj = new Project();
        newProj.setProjName("Project 5");
        User user = ur.findByUsername("John");
        newProj.setUser(user);
        boolean result = ps.createProject(newProj);
        assertTrue(result);

        // Verify that the res was saved
        Project savedProj = pr.findByProjName("Project 5").orElse(null);
        assertNotNull(savedProj);
        assertEquals("Project 5", savedProj.getProjName());

        // Test case: Trying to create the same project again
        result = ps.createProject(newProj);
        assertFalse(result);
    }

    @Test
    void testUpdateProject(){
        Project proj = new Project();
        proj.setUser(ur.findByUsername("Ivy"));
        proj.setProjName("Project 2");
        pr.save(proj);
        String newProjName = "Project 3";
        Project updatedProj = ps.updateProject(proj, newProjName);
        assertEquals(newProjName, updatedProj.getProjName());
    }

    @Test
    void testDeleteProject(){
        Project proj = new Project();
        proj.setUser(ur.findByUsername("Ivy"));
        proj.setProjName("Project 5");
        pr.save(proj);
        boolean res = ps.deleteProject(proj);
        assertTrue(res);
    }


}
