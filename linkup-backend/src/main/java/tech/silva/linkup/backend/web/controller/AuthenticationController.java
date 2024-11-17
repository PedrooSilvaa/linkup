package tech.silva.linkup.backend.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.silva.linkup.backend.entity.UserEntity;
import tech.silva.linkup.backend.exception.InvalidCredencialException;
import tech.silva.linkup.backend.jwt.JwtToken;
import tech.silva.linkup.backend.jwt.JwtUserDetailsService;
import tech.silva.linkup.backend.repository.IUserRepository;
import tech.silva.linkup.backend.web.dto.UserLoginDto;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;

    public AuthenticationController(JwtUserDetailsService detailsService, AuthenticationManager authenticationManager, IUserRepository userRepository) {
        this.detailsService = detailsService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authentication(@RequestBody @Valid UserLoginDto dto) {
        var username = "";
        try {
            username = dto.user();
            if (!dto.user().contains("@")){
                UserEntity userEntity = new UserEntity();
                userEntity = userRepository.findByUser(dto.user());
                username = userEntity.getUsername();
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, dto.password());

            authenticationManager.authenticate(authenticationToken);

            JwtToken token = detailsService.getTokenAuthenticated(username);

            return ResponseEntity.ok(token);
        }catch (AuthenticationException ex) {
            throw new InvalidCredencialException("Invalid credencial " + username);
        }
    }
}
