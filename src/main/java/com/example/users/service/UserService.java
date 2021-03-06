package com.example.users.service;

import com.example.users.controller.UserNameExistException;
import com.example.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user) throws UserNameExistException;
    User getUserById(Integer id);
    void delete(Integer id);
    void updateUser(User user) throws UserNameExistException;
    List<User> getAllUsers();
    Optional<User> getUserByUserName(String userName);
}
