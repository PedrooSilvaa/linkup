package tech.silva.linkup.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.silva.linkup.backend.entity.Like;

public interface ILikeRepository extends JpaRepository<Like, Long> {
}
