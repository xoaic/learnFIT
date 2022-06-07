package com.example.se2project.service;

import com.example.se2project.entity.User;


public interface UserService extends BaseService<User, Long> {
    boolean isExistedEmail(String email);

}
