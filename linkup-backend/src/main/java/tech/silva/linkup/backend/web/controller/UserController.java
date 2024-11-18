package tech.silva.linkup.backend.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tech.silva.linkup.backend.entity.UserEntity;
import tech.silva.linkup.backend.jwt.JwtUserDetails;
import tech.silva.linkup.backend.service.UserService;
import tech.silva.linkup.backend.web.dto.UserCreateDto;
import tech.silva.linkup.backend.web.dto.UserProfileResponseDto;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserProfileResponseDto> saveUser(@RequestBody @Valid UserCreateDto userCreateDto){
        UserEntity user = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserProfileResponseDto.toResponse(user));
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('CLIENT') OR hasRole('ADMIN')")
    public ResponseEntity<UserProfileResponseDto> findUserLogged(@AuthenticationPrincipal JwtUserDetails userDetails){
        UserEntity user = userService.findById(userDetails.getId());
        return ResponseEntity.ok().body(UserProfileResponseDto.toResponse(user));
    }
}
