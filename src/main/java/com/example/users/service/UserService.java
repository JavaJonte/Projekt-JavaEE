package com.example.users.service;

import com.example.users.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);
    User getUserById(Integer id);
    void updateUser(Integer id);
    void deleteUser(User user);
    List<User> getAllUsers();
    Optional<User> findByUserName(String userName);
}
