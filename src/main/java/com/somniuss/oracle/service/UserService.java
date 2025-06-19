package com.somniuss.oracle.service;

import com.somniuss.oracle.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUserById(Long id);
}
