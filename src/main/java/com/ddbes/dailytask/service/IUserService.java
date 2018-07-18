package com.ddbes.dailytask.service;

import com.ddbes.dailytask.entity.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity creatUser(User user);

    ResponseEntity updateUser(User user);

    ResponseEntity deleteUser(Long userId);

    ResponseEntity login(String loginName, String password);
}
