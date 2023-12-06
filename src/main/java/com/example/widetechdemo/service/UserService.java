package com.example.widetechdemo.service;

import com.example.widetechdemo.dao.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void createUser(User user);
    User updateUser(int id, User user);
    void deleteUser(int id);
}
