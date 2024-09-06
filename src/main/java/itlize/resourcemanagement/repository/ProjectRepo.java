package itlize.resourcemanagement.repository;

import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    boolean existsByProjNameAndUser(String username, User user);

    Optional<Project> findByProjName(String projName);

    Optional<Project> findByProjNameAndUser(String projName, User user);

    Optional<Project> findByUser(User user);

    List<Project> findAllByUser(User user);

    Optional<Project> findByIdAndUser(Long projId, User user);
}
