package com.example.beprojectweb.repository;

import com.example.beprojectweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByUsername(String username); // check user

    Optional<User> findUsersByUsername(String username);
}
