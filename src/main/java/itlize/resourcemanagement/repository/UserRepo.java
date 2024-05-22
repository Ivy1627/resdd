package itlize.resourcemanagement.repository;

import itlize.resourcemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    Optional<User> findById(Long id);
}
