package tech.silva.linkup.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.linkup.backend.entity.Like;
import tech.silva.linkup.backend.entity.Post;
import tech.silva.linkup.backend.entity.UserEntity;
import tech.silva.linkup.backend.exception.LikeAlreadyMadeException;
import tech.silva.linkup.backend.exception.ObjectNotFoundException;
import tech.silva.linkup.backend.repository.ILikeRepository;
import tech.silva.linkup.backend.repository.IPostRepository;
import tech.silva.linkup.backend.repository.IUserRepository;

@Service
public class LikeService {

    private final ILikeRepository likeRepository;
    private final IUserRepository userRepository;
    private final IPostRepository postRepository;

    public LikeService(ILikeRepository likeRepository, IUserRepository userRepository, IPostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Like createLike(Long idUser, Long idPost){
        UserEntity user = userRepository.findById(idUser)
                .orElseThrow(
                        () -> new ObjectNotFoundException(String.format("User not found. Please check the user ID and try again."))
                );
        Post post = postRepository.findById(idPost)
                .orElseThrow(
                        () -> new ObjectNotFoundException(String.format("Post not found. Please check the user ID and try again."))
                );

        if (likeRepository.existsByUserAndPost(user, post)){
            throw new LikeAlreadyMadeException("Post already liked");
        }

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        return likeRepository.save(like);
    }


}
