package tech.silva.linkup.backend.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.linkup.backend.entity.UserEntity;
import tech.silva.linkup.backend.exception.ObjectNotFoundException;
import tech.silva.linkup.backend.exception.UserUniqueViolationException;
import tech.silva.linkup.backend.repository.IUserRepository;
import tech.silva.linkup.backend.web.dto.UserCreateDto;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserEntity createUser(UserCreateDto dto) {
        if (userRepository.existsByUser(dto.user())) {
            throw new UserUniqueViolationException(
                    String.format("User = %s already exists. Please use a different user.", dto.user())
            );
        }
        if (userRepository.existsByUsername(dto.username())) {
            throw new UserUniqueViolationException(
                    String.format("Username = %s already exists. Please use a different username.", dto.username())
            );
        }

        UserEntity user = new UserEntity();
        user.setUser(dto.user());
        user.setBirthDate(dto.birthDate());
        user.setPhone(dto.phone());
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserEntity findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(String.format("User not found. Please check the user ID and try again."))
                );
    }

    @Transactional(readOnly = true)
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new ObjectNotFoundException(String.format("User not found. Please check the user username and try again."))
                );
    }
}
