package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.repository.ResourceRepo;
import itlize.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceRepo rr;

    @Override
    public boolean createResource(Resource res){
        if (rr.existsByResNameOrResCode(res.getResName(), res.getResCode())) {
            return false;
        }
        rr.save(res);
        return true;
    }

    @Override
    public Resource updateResourceName(Resource res, String resourceName){
        res.setResName(resourceName);
        rr.save(res);
        return res;
    }

    @Override
    public boolean deleteResource(Resource res){
        if (!rr.existsByResNameOrResCode(res.getResName(), res.getResCode())) {
            return false;
        }
        rr.delete(res);
        return true;
    }
}
