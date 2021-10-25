package com.example.users;

import com.example.users.controller.UserRepository;
import com.example.users.model.User;
import com.example.users.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class UsersApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UsersApplication.class, args);
        UserService userService = context.getBean(UserService.class);

        // DEMO-OBJEKT
        User test = new User("Hej", "Då", "email@email.se", "test","test");
        User test2 = new User("Nästa", "Person", "annanepost", "anbat ","sa");
        User test3 = new User("asdsad", "dgsaa", "da", "admin","admin");

        // SKAPA DEMO-OBJEKT
        userService.createUser(test);
        userService.createUser(test2);
        userService.createUser(test3);

        // TESTUTSKRIFT
        System.out.println("DETTA SKRIVS EFTER TILLAGT");

        //TESTUTSKRIFT LISTA
        System.out.println(userService.getAllUsers());

        // TEST DELETE
        userService.deleteUser(test2);

        // TEST LISTA IGEN
        System.out.println(userService.getAllUsers());

        // TEST GET ONE
        System.out.println(userService.getUserById(1));

        //UPPDATERA USER





    }





}

