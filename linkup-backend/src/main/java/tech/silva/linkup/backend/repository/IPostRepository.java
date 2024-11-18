package tech.silva.linkup.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.silva.linkup.backend.entity.Post;
import tech.silva.linkup.backend.entity.UserEntity;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(UserEntity user);
}
