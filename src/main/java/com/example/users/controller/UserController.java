package com.example.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository repo;

    @RequestMapping("/")
    public String getIndex(@RequestParam(value = "id", required = false) Integer id, Model model) {

        System.out.println("SER VI DETTA I LOGGEN?");
        model.addAttribute("id", id);
        return "index";
    }

    @RequestMapping("/createAccount")
    public String postAccount(@RequestParam(value = "user", required = false) String user,
                              @RequestParam(value = "firstName", required = false) String firstName,
                              @RequestParam(value = "lastName", required = false)String lastName,
                              @RequestParam(value = "email", required = false)String email,
                              @RequestParam(value = "password", required = false)String password,
                              @RequestParam(value = "rePassword", required = false)String rePassword,
                              @RequestParam(value = "deletePassword", required = false)String deletePassword,
                              Model model){


            model.addAttribute("user", user);
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("rePassword", rePassword);
            model.addAttribute("deletePassword", deletePassword);


            return "createAccount";


    }
    @RequestMapping("/login")
    public String checkLogin(@RequestParam(value = "user", required = false)String user,
                             @RequestParam(value = "password", required = false)String password,
                             Model model){

            model.addAttribute("user", user);
            model.addAttribute("password", password);


            return "login";



    }
    @RequestMapping("/forgotPassword")
    public String getPassword(){
        return "forgotPassword";
    }
    @RequestMapping("/login/accountManagement")
    public String showAccount(){
        return "accountManagement";
    }
    @RequestMapping("/updateAccount")
    public String updateAccount(){
        return "updateAccount";
    }


}
