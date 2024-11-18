package tech.silva.linkup.backend.service;

import org.springframework.stereotype.Service;
import tech.silva.linkup.backend.repository.ILikeRepository;

@Service
public class LikeService {

    private final ILikeRepository likeRepository;

    public LikeService(ILikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
}
