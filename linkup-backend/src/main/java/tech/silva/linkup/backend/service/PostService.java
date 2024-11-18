package tech.silva.linkup.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.linkup.backend.entity.Post;
import tech.silva.linkup.backend.entity.UserEntity;
import tech.silva.linkup.backend.exception.ObjectNotFoundException;
import tech.silva.linkup.backend.repository.IPostRepository;
import tech.silva.linkup.backend.repository.IUserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final IPostRepository postRepository;
    private final IUserRepository userRepository;

    public PostService(IPostRepository postRepository, IUserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Post createPost(Long id, String description){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(String.format("User not found. Please check the user ID and try again."))
                );
        Post post = new Post();
        post.setUser(user);
        post.setDescription(description);
        post.setDateTime(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> listPost(){
        return postRepository.findAll();
    }

    public List<Post> listMyPost(Long id) {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(
                    () -> new ObjectNotFoundException(String.format("User not found. Please check the user ID and try again."))
            );
        return postRepository.findAllByUser(user);
    }
}
