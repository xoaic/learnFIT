package com.example.se2project.service;

import com.example.se2project.entity.User;

public interface AuthService {
    User existedUser(String email, String password);
}
