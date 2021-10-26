package com.example.users.service;

import com.example.users.controller.UserNameExistException;
import com.example.users.model.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface UserService {

    void createUser(User user);
    User getUserById(Integer id);
    void delete(Integer id);
    void saveUser(User user) throws UserNameExistException;
    List<User> getAllUsers();
}
