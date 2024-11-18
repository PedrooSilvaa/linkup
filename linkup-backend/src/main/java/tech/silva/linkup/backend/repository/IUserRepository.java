package tech.silva.linkup.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.silva.linkup.backend.entity.UserEntity;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUser(String user);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    UserEntity findByUser(String user);
}
