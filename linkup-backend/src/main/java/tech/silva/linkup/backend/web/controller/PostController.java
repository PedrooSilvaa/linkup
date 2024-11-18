package tech.silva.linkup.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.silva.linkup.backend.entity.Post;
import tech.silva.linkup.backend.jwt.JwtUserDetails;
import tech.silva.linkup.backend.service.PostService;
import tech.silva.linkup.backend.web.dto.PostCreateDto;
import tech.silva.linkup.backend.web.dto.PostResponseDto;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal JwtUserDetails userDetails, @RequestBody PostCreateDto dto){
        Post post = postService.createPost(userDetails.getId(), dto.description());
        return ResponseEntity.status(HttpStatus.CREATED).body(PostResponseDto.toResponse(post));
    }
}
