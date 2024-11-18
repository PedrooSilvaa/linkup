package tech.silva.linkup.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.silva.linkup.backend.entity.Post;

public interface IPostRepository extends JpaRepository<Post, Long> {
}
