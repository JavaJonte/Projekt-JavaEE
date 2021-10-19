package com.example.users.controller;

import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @Autowired
    UserService repo;


    @RequestMapping("/myAccount")
    public String getIndex(Integer id, Model model) {

        System.out.println("INDEX SYNS I LOGGEN?"); // VISAS I KONSOLL VARJE GÅNG SIDAN UPPDATERAS
        System.out.println(repo.getAllUsers()); // KÖR EN QUERY MOT DATABASEN OCH LOGGAR...
        return "index";                         // ...DET, PLUS LISTAN PÅ ALLA ANVÄNDARE I KONSOLLEN
    }

    @RequestMapping("/user")
    public String getUser(Integer id, Model model) {
        System.out.println("GET USER I LOGGEN");


        return "user";
    }


}
