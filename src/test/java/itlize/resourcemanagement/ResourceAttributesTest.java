package itlize.resourcemanagement;

import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.ResourceAttributes;
import itlize.resourcemanagement.repository.ResourceAttributesRepo;
import itlize.resourcemanagement.repository.ResourceRepo;
import itlize.resourcemanagement.service.ResourceAttributesService;
import itlize.resourcemanagement.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ResourceAttributesTest {
    @Autowired
    private ResourceAttributesRepo rar;
    @Autowired
    private ResourceRepo rr;
    @Autowired
    private ResourceAttributesService ras;
    @Test
    void testCreateColVal(){
        ResourceAttributes resourceAttributes = new ResourceAttributes();

        // Create a Resource object
        Resource resource = new Resource();
        resource.setResName("Water");
        resource.setResCode(109243);
        // Save the Resource object
        Resource savedResource = rr.save(resource);

        // Define column name and column value
        String colName = "condition";
        String colVal = "new";

        // Call the createColVal method
        boolean result = ras.createColVal(resourceAttributes, savedResource, colName, colVal);

        // Assert that the method returns true (indicating successful creation)
        assertTrue(result, "ResourceAttributes should be created successfully");

        // Fetch the saved ResourceAttributes from the database
        ResourceAttributes savedResourceAttributes = rar.findById(resourceAttributes.getId()).orElse(null);

        // Assert that the saved ResourceAttributes object is not null
        assertNotNull(savedResourceAttributes, "Saved ResourceAttributes should not be null");

        // Assert that the column name and column value are correctly set
        assertEquals(savedResourceAttributes.getRes().getId(), savedResource.getId(), "Resource should be set correctly");
        assertEquals(savedResourceAttributes.getColName(), colName, "Column name should be set correctly");
        assertEquals(savedResourceAttributes.getColVal(), colVal, "Column value should be set correctly");

        // Call the createColVal method again with the same parameters
        boolean duplicateResult = ras.createColVal(resourceAttributes, savedResource, colName, colVal);

        // Assert that the method returns false (indicating duplicate creation attempt)
        assertFalse(duplicateResult, "ResourceAttributes should not be created due to duplication");
    }

    @Test
    void testUpdateResourceAttributes(){
        Resource res = rr.findByResName("Metals").orElse(null);

        ResourceAttributes updatedRA = ras.updateResourceAttributes(res, "good");

        // Verify that the resource name is updated
        assertEquals("good", updatedRA.getColVal());
    }

    @Test
    void testDeleteResource(){
        boolean res = ras.deleteColName("condition");
        assertTrue(res, "Resource should be deleted");
    }
}
