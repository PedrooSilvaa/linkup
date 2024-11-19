package tech.silva.linkup.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.silva.linkup.backend.entity.Like;
import tech.silva.linkup.backend.jwt.JwtUserDetails;
import tech.silva.linkup.backend.service.LikeService;
import tech.silva.linkup.backend.web.dto.LikeResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{idPost}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<LikeResponseDto> saveLike(@PathVariable Long idPost, @AuthenticationPrincipal JwtUserDetails userDetails){
        Like like = likeService.createLike(userDetails.getId(), idPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(LikeResponseDto.toResponse(like));
    }

    @GetMapping("/{idPost}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<LikeResponseDto>> findAllByPost(@PathVariable Long idPost){
        List<Like> like = likeService.findAllByPost(idPost);
        return ResponseEntity.ok().body(LikeResponseDto.toList(like));
    }


}
