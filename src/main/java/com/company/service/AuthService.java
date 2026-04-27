package com.company.service;

import com.company.dto.AuthDTO;
import com.company.entity.User;
import com.company.repository.UserRepository;
import com.company.utils.Utils;

import java.util.Optional;
import java.util.UUID;

public class AuthService {
    private static AuthService authService;
    private AuthService(){};
    public static AuthService getInstance() {
        if (authService == null) {
            authService = new AuthService();
        }
        return authService;
    }

    private final UserRepository userRepository = UserRepository.getInstance();

    public boolean login(AuthDTO authDTO) {
        Optional<User> userByPhone = userRepository.getUserByPhone(authDTO.phoneNumber());
        if (userByPhone.isEmpty()) return false;
        User user = userByPhone.get();
        if (!user.getPassword().equals(authDTO.password())) return false;
        Utils.currentUserId = user.getId();
        return true;
    }

    public boolean registration(AuthDTO authDTO) {
        Optional<User> userByEmail = userRepository.getUserByEmail(authDTO.email());
        Optional<User> userByPhone = userRepository.getUserByPhone(authDTO.phoneNumber());

        if (userByPhone.isPresent() || userByEmail.isPresent()) return false;
        userRepository.saveUser(new User(UUID.randomUUID().toString(), authDTO.fullName(), authDTO.phoneNumber(), authDTO.password(), authDTO.email()));
        return true;
    }
}
