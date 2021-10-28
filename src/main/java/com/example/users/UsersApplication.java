package com.example.users;

import com.example.users.controller.UserNameExistException;
import com.example.users.model.User;
import com.example.users.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) throws UserNameExistException {
        ConfigurableApplicationContext context = SpringApplication.run(UsersApplication.class, args);
        UserService userService = context.getBean(UserService.class);

        // DEMO-OBJEKT
        User test = new User("HejHejHejHejHej", "DåHejHejHejHej", "email@email.se", "test", "test", "hemligt", "ROLE_ADMIN");
        userService.createUser(test);

    }
}