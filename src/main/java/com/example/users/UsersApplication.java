package com.example.users;

import com.example.users.model.User;
import com.example.users.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }


    User test = new User(1,"Hej", "Då", "email", "HDÅ","KOD");

    UserServiceImpl hej = new UserServiceImpl();


}
