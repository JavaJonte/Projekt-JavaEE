package com.example.users.service;

import com.example.users.model.User;
import java.util.List;

public interface UserService {

    void createUser(User user);
    User getUserById(Integer id);
    void updateUser(Integer id);
    void deleteUser(User user);
    List<User> getAllUsers();
}
