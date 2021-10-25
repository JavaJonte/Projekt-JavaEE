package com.example.users.service;

import com.example.users.controller.UserNameExistException;
import com.example.users.controller.UserRepository;
import com.example.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        return repo.findById(id).get();
        // FEL HANTERING
    }

    @Override
    public void deleteUser(User user) {
        repo.delete(user);
    }
    @Override
    public void saveUser(User user) throws UserNameExistException {
       Optional<User> userOptional = repo.findUserByUserName(user.getUserName());
        if(userOptional.isPresent()){
            throw new UserNameExistException("Användarnamnet är upptaget");
        }
        this.repo.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
