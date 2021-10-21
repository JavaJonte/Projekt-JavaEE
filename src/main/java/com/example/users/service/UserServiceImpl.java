package com.example.users.service;

import com.example.users.controller.UserRepository;
import com.example.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public void createUser(User newUser) {

        List<User> users = getAllUsers();

        if(users.stream().anyMatch(u -> u.getUserName() == newUser.getUserName()))
            throw new EntityExistsException();
        else {
            repo.save(newUser);
            System.out.println("USER NOW SAVED TO DB");
        }

    }

    @Override
    public User getUserById(Integer id) {

        Optional<User> user = repo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else throw new EntityNotFoundException();
    }

    @Override
    @Transactional
    public void updateUser(Integer id) {
    }

    @Override
    public String deleteUser(User user) {

            if(repo.existsById(user.getId())){
                repo.delete(user);
                return "Användaren med id " + user.getId() + " är raderad ur databasen";
            } else {
                return "TOKIGT! Vi kunde inte hitta någon användare med id " + user.getId() + " i vår databas";
            }

    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
