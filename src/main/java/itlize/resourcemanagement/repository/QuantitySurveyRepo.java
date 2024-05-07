package itlize.resourcemanagement.repository;

import itlize.resourcemanagement.entity.QuantitySurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantitySurveyRepo extends JpaRepository<QuantitySurvey, Long> {
    boolean existsByColNameAndType(String colName, QuantitySurvey.Type type);

    List<QuantitySurvey> findByColName(String colName);
    //int updateByColName(String colName);
}
