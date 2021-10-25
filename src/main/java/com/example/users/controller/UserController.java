package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.service.UserService;
import com.example.users.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String getIndex(@RequestParam(value = "id", required = false) Integer id, Model model) {

        System.out.println("SER VI DETTA I LOGGEN?");
        model.addAttribute("id", id);
        return "index";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String postAccount(@RequestParam(value = "user", required = false) String user,
                              @RequestParam(value = "firstName", required = false) String firstName,
                              @RequestParam(value = "lastName", required = false)String lastName,
                              @RequestParam(value = "email", required = false)String email,
                              @RequestParam(value = "password", required = false)String password,
                              @RequestParam(value = "rePassword", required = false)String rePassword,
                              @RequestParam(value = "deletePassword", required = false)String deletePassword,
                              @RequestParam(value = "active", required = false)boolean active,
                              @RequestParam(value = "roles", required = false)String roles,
                              Model model){


            model.addAttribute("user", user);
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("rePassword", rePassword);
            model.addAttribute("deletePassword", deletePassword);
            model.addAttribute("active", active);
            model.addAttribute("roles", roles);


            return "createAccount";


    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String checkLogin(Model model){
            model.addAttribute("users", new Users());
            return "login";
    }
    @RequestMapping(value = "/login/accountManagement", method = RequestMethod.POST)
    public String logOn(@ModelAttribute Users users, Model model){
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


    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable ( value = "id") Integer id, Model model){
        User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "updateUser";

    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes ra) {
        try {
            userService.createUser(user);
            ra.addFlashAttribute("message", "Användaren har uppdaterats");

        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/";
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




    // ADMIN FEELING
    // TODO Se över om nedan ska ligga i samma fil eller om den skall flyttas ut..

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "userList";
    }


}
