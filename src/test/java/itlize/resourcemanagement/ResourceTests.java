package itlize.resourcemanagement;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.ProjectRepo;
import itlize.resourcemanagement.repository.ResourceRepo;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.ProjectService;
import itlize.resourcemanagement.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ResourceTests {
    @Autowired
    private ResourceRepo rr;
    @Autowired
    private ResourceService rs;
    @Autowired
    private ProjectRepo pr;
    @Autowired
    private UserRepo ur;

    @Test
    void testCreateResource(){
        Resource newRes = new Resource();
        newRes.setResName("Concrete");
        newRes.setResCode(1000000);
        boolean result = rs.createResource(newRes);
        assertTrue(result);

        // Verify that the res was saved
        Resource savedRes = rr.findByResName("Concrete").orElse(null);
        assertNotNull(savedRes);
        assertEquals("Concrete", savedRes.getResName());

        // Test case: Trying to create the same user again
        result = rs.createResource(newRes);
        assertFalse(result);
    }

    @Test
    void testUpdateResourceName(){
        Resource resource = new Resource();
        resource.setResName("Mansory");
        rr.save(resource);

        String newResourceName = "Unit Mansory";

        Resource updatedResource = rs.updateResourceName(resource, newResourceName);

        // Verify that the resource name is updated
        assertEquals(newResourceName, updatedResource.getResName());
    }

    @Test
    void testDeleteResource(){
        Resource res = new Resource();
        res.setResName("Mansory");
        res.setResCode(123456);
        rr.save(res);
        rs.deleteResource(res);
        Resource deletedResource = rr.findByResName(res.getResName()).orElse(null);
        assertNull(deletedResource, "Resource should be deleted");
    }

    @Test
    void testAddToProject(){
        Project project = pr.findByProjNameAndUser("Project 1", ur.findByUsername("Ivy")).orElse(null);
        List<Resource> resources = rr.findAllWithAttr();
        rs.addToProject(project, resources);
    }
}
