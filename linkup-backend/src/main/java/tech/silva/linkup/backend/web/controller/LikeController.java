package tech.silva.linkup.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.silva.linkup.backend.entity.Like;
import tech.silva.linkup.backend.jwt.JwtUserDetails;
import tech.silva.linkup.backend.service.LikeService;
import tech.silva.linkup.backend.web.dto.LikeResponseDto;

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
        Like like = likeService.createLike(idPost, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(LikeResponseDto.toResponse(like));
    }

}
