package com.example.users;

import com.example.users.model.User;
import com.example.users.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UsersApplication.class, args);
        UserService userService = context.getBean(UserService.class);

        // DEMO-OBJEKT
        User test = new User("Boss", "Man", "email@email.com", "admin", "admin", "hemligt");
        // MODIFIERA DEMO-OBJEKT
        test.setAdmin(true); // Sätter objektet till admin
        test.setActive(true); // Sätter objektet till aktiv
        userService.createUser(test); // Sparar objekt i databas

        // TESTUTSKRIFT
        System.out.println("OBJEKTET OVAN HAR LAGTS TILL I DATABASEN");

        //TESTUTSKRIFT LISTA
        System.out.println(userService.getAllUsers());


    }
}