package com.example.users;

import com.example.users.model.User;
import com.example.users.service.UserService;
import com.example.users.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) {
       ConfigurableApplicationContext context = SpringApplication.run(UsersApplication.class, args);
       UserService userService = context.getBean(UserService.class);

        User test = new User(1,"Hej", "Då", "email", "HDÅ","KOD");

       userService.createUser(test);

        System.out.println("DETTA SKRIVS EFTER TILLAGT");





    }





}

