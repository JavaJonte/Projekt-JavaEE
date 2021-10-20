package com.example.users.service;

import com.example.users.controller.UserRepository;
import com.example.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

        Optional<User> user = repo.findById(id);
        if(user.isPresent())
            return user.get();
        else return null;  // vill istället kasta ett exception här för att slippa null-värde,
        // eller flytta ut felhantering utanför metoden,
        // men känns som man vill ha error-handling i denna class ?
    }

    @Override
    @Transactional
    public void updateUser(Integer id) {
    }

    @Override
    public void deleteUser(User user) {

            if(repo.existsById(user.getId())){
                repo.delete(user);
            } else {
                System.out.println("Vi hittar ingen med id " + user.getId() +" i vår databas");
            }

    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
