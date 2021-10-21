package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    //UserRepository repo;

    @RequestMapping("/")
    public String getIndex(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id, Model model) {

        System.out.println("SER VI DETTA I LOGGEN?");
        System.out.println(userService.getUserById(id));
        model.addAttribute("id", id);
        return "index";
    }

    @RequestMapping(value ="/createAccount", method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "userName", required = true) String userName,
                              @RequestParam(value = "firstName", required = true) String firstName,
                              @RequestParam(value = "lastName", required = true)String lastName,
                              @RequestParam(value = "email", required = true)String email,
                              @RequestParam(value = "password", required = true)String password,
                              @RequestParam(value = "rePassword", required = false)String rePassword,
                              @RequestParam(value = "deletePassword", required = false)String deletePassword,
                              Model model){

        User user = new User(firstName, lastName, email, userName, password);
        userService.createUser(user);
        System.out.println(userService.getAllUsers());

            model.addAttribute("user", user);  // <-- user är nu ett helt pojo istället för en sträng
           // model.addAttribute("firstName", firstName); // denna rad kan tas bort
          //  model.addAttribute("lastName", lastName); // denna rad kan tas bort
           // model.addAttribute("email", email); // denna rad kan tas bort
          //  model.addAttribute("password", password); // denna rad kan tas bort
          //  model.addAttribute("rePassword", rePassword);
           // model.addAttribute("deletePassword", deletePassword);

            return "createdTestFile";
           // return "createAccount";


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
