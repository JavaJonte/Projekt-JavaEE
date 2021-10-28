package com.example.users.service;

import com.example.users.controller.UserNameExistException;
import com.example.users.controller.UserRepository;
import com.example.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void updateUser(User user) throws UserNameExistException {

        List<User> userList = getAllUsers();
        Optional<User> user0 = userList.stream().filter(u -> u.getUserName().equals(user.getUserName())).findFirst();

        if(user0.isPresent()){
            System.out.println("HALLÅ "+user0.get().toString());
        }

        boolean isUserNamePresent = userList.stream().anyMatch(u -> u.getUserName().equals(user.getUserName()));

        if(isUserNamePresent){
            throw new UserNameExistException("Användarnamnet är upptaget");
        } else {
            repo.save(user);
        }
    }


    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return repo.findUserByUserName(userName);
    }
}
