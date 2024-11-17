package tech.silva.linkup.backend.service;

import org.springframework.stereotype.Service;
import tech.silva.linkup.backend.repository.IUserRepository;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
