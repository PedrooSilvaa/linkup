package tech.silva.linkup.backend.service;

import org.springframework.stereotype.Service;
import tech.silva.linkup.backend.repository.IPostRepository;

@Service
public class PostService {

    private final IPostRepository postRepository;

    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
