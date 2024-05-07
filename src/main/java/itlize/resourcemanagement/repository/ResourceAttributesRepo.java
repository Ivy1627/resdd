package itlize.resourcemanagement.repository;

import itlize.resourcemanagement.entity.ResourceAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceAttributesRepo extends JpaRepository<ResourceAttributes, Long> {
    // for locating a blank in the table
    boolean existsByColNameAndColVal(String colName, String colVal);
    boolean existsByColName(String colName);
    List<ResourceAttributes> findAllByColName(String ColName);
}
