package com.example.widetechdemo.service.impl;

import com.example.widetechdemo.Exception.BusinessException;
import com.example.widetechdemo.dao.entity.User;
import com.example.widetechdemo.dao.repository.UserRepository;
import com.example.widetechdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void createUser(User user) {
        try {
            userRepository.save(user);
            logger.info("User created successfully: {}", user.getUsername());
        } catch (DataIntegrityViolationException e) {
            // 处理数据库唯一约束异常
            logger.error("Failed to create user. Username '{}' already exists.", user.getUsername());
            throw new BusinessException("User already exists.", e);
        }
    }

    @Transactional
    public User updateUser(int id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setEmail(user.getEmail());

            return userRepository.save(updatedUser);
        } else {
            logger.warn("User with ID {} not found. Update operation failed.", id);
            return null;
        }
    }

    @Transactional
    public void deleteUser(int id) {
        Optional<User> userToDelete = userRepository.findById(id);
        userToDelete.ifPresentOrElse(
                user -> {
                    userRepository.delete(user);
                    logger.info("User with ID {} deleted successfully.", id);
                },
                () -> {
                    String errorMessage = String.format("User with ID %d not found. Delete operation failed.", id);
                    logger.warn(errorMessage);
                    throw new BusinessException(errorMessage);                }
        );
    }
}
