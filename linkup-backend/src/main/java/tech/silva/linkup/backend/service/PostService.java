package tech.silva.linkup.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.linkup.backend.entity.Post;
import tech.silva.linkup.backend.entity.UserEntity;
import tech.silva.linkup.backend.exception.ObjectNotFoundException;
import tech.silva.linkup.backend.repository.IPostRepository;
import tech.silva.linkup.backend.repository.IUserRepository;
import tech.silva.linkup.backend.web.dto.PostResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final IPostRepository postRepository;
    private final IUserRepository userRepository;
    private final LikeService likeService;

    public PostService(IPostRepository postRepository, IUserRepository userRepository, LikeService likeService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeService = likeService;
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
    public List<PostResponseDto> listPost(Long idUser){
        UserEntity user = userRepository.findById(idUser)
                .orElseThrow(
                        () -> new ObjectNotFoundException(String.format("User not found. Please check the user ID and try again."))
                );
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postDtos = PostResponseDto.toList(posts);

        postDtos = postDtos.stream()
                .map(postDto -> {
                    Post post = postRepository.findById(postDto.id())
                            .orElseThrow(() -> new ObjectNotFoundException(
                                    String.format("Post not found. Please check the user ID and try again.")));

                    return new PostResponseDto(post.getId(), post.getUser().getUser(), post.getDescription(), likeService.liked(user, post), likeService.countLike(post));
                })
                .collect(Collectors.toList());

        return postDtos;
    }

    public List<Post> listMyPost(Long id) {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(
                    () -> new ObjectNotFoundException(String.format("User not found. Please check the user ID and try again."))
            );
        return postRepository.findAllByUser(user);
    }
}
