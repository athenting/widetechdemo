package com.example.widetechdemo.dao.repository;

import com.example.widetechdemo.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Custom queries or methods if needed
}
