package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService repo;

    @RequestMapping("/")
    public String getIndex() {

        // VISAS I KONSOLL VARJE GÅNG SIDAN UPPDATERAS
       // System.out.println(repo.getAllUsers());     // KÖR EN QUERY MOT DATABASEN OCH LOGGAR...
        System.out.println("INDEX SYNS I LOGGEN?"); // ...DET, PLUS LISTAN PÅ ALLA ANVÄNDARE
       // System.out.println(repo.getUserById(3));    // SKRIVER UT ID 3 I KONSOLL

        return "index.html"; // RETURNERAR index.html (SKA SENARE BYTAS TILL MYACCOUNT)
    }

    @RequestMapping("/user")
    public String getUser() {

        // VISAS I KONSOLL VARJE GÅNG SIDAN UPPDATERAS
        System.out.println("GET USER I LOGGEN");
      //  System.out.println(repo.getUserById(1));    // SKRIVER UT ID 1 I KONSOLL

        return "user.html"; // RETURNERAR user.html
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmin() {
        System.out.println("GET ADMIN I LOG");
        return "admin.html";
    }



}