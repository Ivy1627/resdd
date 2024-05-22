package itlize.resourcemanagement.repository;

import itlize.resourcemanagement.entity.Resource;
import itlize.resourcemanagement.entity.ResourceAttributes;
import itlize.resourcemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {
    boolean existsByResNameOrResCode(String projName, int resCode);

    Optional<Resource> findByResName(String resName);

    @Query("SELECT r FROM Resource r LEFT JOIN FETCH r.resAttributes")
    List<Resource> findAllWithAttr();
}
