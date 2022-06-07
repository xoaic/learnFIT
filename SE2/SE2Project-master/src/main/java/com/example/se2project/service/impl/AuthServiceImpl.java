package com.example.se2project.service.impl;

import com.example.se2project.entity.User;
import com.example.se2project.repository.UserRepository;
import com.example.se2project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User existedUser(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email.toLowerCase(), password);
    }
}
