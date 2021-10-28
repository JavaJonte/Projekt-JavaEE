package com.example.users.service;

import com.example.users.controller.UserNameExistException;
import com.example.users.controller.UserRepository;
import com.example.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public void createUser(User user) throws UserNameExistException {
        Optional<User> u = repo.findUserByUserName(user.getUserName());
        if (u.isPresent()){
            throw new UserNameExistException("Användarnamnet är upptaget");
        }
        repo.save(user);


    }

    @Override
    public User getUserById(Integer id) {
        return repo.findById(id).get();
        // FEL HANTERING
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void saveUser(User user) throws UserNameExistException {
        Optional<User> userOptional = repo.findUserByUserName(user.getUserName());
        if(userOptional.isPresent()){
            throw new UserNameExistException("Användarnamnet är upptaget");
        } else {
            repo.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }


}
