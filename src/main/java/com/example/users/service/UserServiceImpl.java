package com.example.users.service;

import com.example.users.controller.UserRepository;
import com.example.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public void createUser(User user) {
        repo.save(user);
        System.out.println("USER NOW SAVED TO DB");
    }

    @Override
    public User getUserById(Integer id) {
        return repo.findById(id).get();
        // FEL HANTERING
    }

    @Override
    @Transactional
    public void updateUser(Integer id,String firstName, String lastName, String email, String userName, String password) {
        repo.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "Användare med id " + id + " finns inte"
                ));

    }

    @Override
    public void deleteUser(User user) {
        repo.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
