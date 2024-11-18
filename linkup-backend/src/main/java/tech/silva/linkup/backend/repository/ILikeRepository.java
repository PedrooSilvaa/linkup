package tech.silva.linkup.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.silva.linkup.backend.entity.Like;
import tech.silva.linkup.backend.entity.Post;
import tech.silva.linkup.backend.entity.UserEntity;

public interface ILikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUserAndPost(UserEntity user, Post post);
}
