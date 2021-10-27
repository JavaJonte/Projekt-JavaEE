package com.example.users.controller;

import com.example.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional <User> findUserByUserName(String userName);
    // Optional<User> findUserByEmail(String email); // Metoden används aldrig.
}
