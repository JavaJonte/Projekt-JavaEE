package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.model.Users;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    //UserRepository repo;

    @RequestMapping("/")
    public String getIndex(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id, Model model) {

        System.out.println("SER VI DETTA I LOGGEN?");
        User user = userService.getUserById(id);

        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value= "/createAccount", method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "user", required = false) String userName,
                              @RequestParam(value = "firstName", required = false) String firstName,
                              @RequestParam(value = "lastName", required = false)String lastName,
                              @RequestParam(value = "email", required = false)String email,
                              @RequestParam(value = "password", required = false)String password,
                              @RequestParam(value = "rePassword", required = false)String rePassword,
                              @RequestParam(value = "deletePassword", required = false)String deletePassword,
                              Model model){
        User user = new User(firstName, lastName, email, userName, password);
        userService.createUser(user);

            model.addAttribute("user", user);
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("rePassword", rePassword);
            model.addAttribute("deletePassword", deletePassword);
            return "createAccount";


    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String checkLogin(Model model){
            model.addAttribute("users", new Users());
            return "login";
    }
    @RequestMapping(value = "/login/accountManagement", method = RequestMethod.POST)
    public String logOn(@ModelAttribute Users users, Model model){

        // if users.userName exists  -> check password

        model.addAttribute("users", users);
    return "accountManagement";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String getPassword(Model model){
        model.addAttribute("users", new Users());
        return "forgotPassword";
    }
    @RequestMapping(value = "/forgotPassword/passRecovery", method = RequestMethod.POST)
    public String postSent(@ModelAttribute Users users, Model model){

        model.addAttribute("users", users);
        return "passRecovery";
    }
    @RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
    public String updateAccount(Model model){
        model.addAttribute("users", new Users());
        return "updateAccount";
    }

    @RequestMapping(value = "/updateAccount/updated/accountManagement", method = RequestMethod.POST)
    public String updated(@ModelAttribute Users users, Model model){
        model.addAttribute("users", users);
        return "accountManagement";
    }


}
