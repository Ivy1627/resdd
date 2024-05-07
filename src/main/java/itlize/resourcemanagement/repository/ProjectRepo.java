package itlize.resourcemanagement.repository;

import itlize.resourcemanagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    boolean existsByProjName(String username);

    Optional<Project> findByProjName(String projName);
}
