package com.project.voting.service;

import com.project.voting.entity.User;
import com.project.voting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public User createUser(String name, String email, String role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        user.setPasswordHash("dummy-password");
        user.setEncryptedPrivateKey("dummy");
        user.setPublicKey("dummy");
        user.setMfaSecret("dummy");
        return userRepo.save(user);
    }

}
