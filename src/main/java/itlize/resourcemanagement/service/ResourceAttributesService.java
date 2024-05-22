package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.ResourceAttributes;

public interface ResourceAttributesService {
    public boolean createColName(String colName);
    // delete all the col with colName
    public boolean deleteColName(String colName);
    public boolean createColVal(ResourceAttributes resAttr, Resource res, String colName, String colVal);

    public ResourceAttributes updateResourceAttributes(Resource res, String colVal);
}
