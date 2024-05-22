package itlize.resourcemanagement.repository;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.QuantitySurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantitySurveyRepo extends JpaRepository<QuantitySurvey, Long> {
    boolean existsByColNameAndType(String colName, QuantitySurvey.Type type);

    boolean existsByColNameAndProj(String colName, Project proj);

    boolean existsByColName(String colName);
    QuantitySurvey findByColNameAndProj(String colName,Project project);

    List<QuantitySurvey> findByProj(Project proj);

    //int updateByColName(String colName);
}
