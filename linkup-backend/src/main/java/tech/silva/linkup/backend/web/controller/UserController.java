package tech.silva.linkup.backend.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.silva.linkup.backend.entity.UserEntity;
import tech.silva.linkup.backend.repository.IUserRepository;
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
}
