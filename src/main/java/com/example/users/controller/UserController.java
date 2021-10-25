package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.service.UserService;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(@ModelAttribute User user, Model model)
    {
        return "index";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String postAccount(Model model)
    {
        model.addAttribute("user", new User());
            return "createAccount";
    }
    @RequestMapping(value = "/login/home", method = RequestMethod.POST)
    public String saveToDB(@ModelAttribute User user, RedirectAttributes ra, Model model)
    {
        model.addAttribute("user", user);
        try {
            userService.createUser(user);
            ra.addFlashAttribute("message", "User created");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "accountManagement";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String checkLogin(Model model)
    {
            model.addAttribute("user", new User());
            return "login";
    }
    @RequestMapping(value = "/login/accountManagement", method = RequestMethod.POST)
    public String logOn(@ModelAttribute User user, Model model)
    {
        model.addAttribute("user", user);
    return "accountManagement";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String getPassword(Model model){
        model.addAttribute("user", new User());
        return "forgotPassword";
    }
    @RequestMapping(value = "/forgotPassword/passRecovery", method = RequestMethod.POST)
    public String postSent(@ModelAttribute User user, Model model)
    {
        model.addAttribute("user", user);
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
        model.addAttribute("user", new User());
        return "updateAccount";

    }

    @RequestMapping(value = "/updateAccount/updated/accountManagement", method = RequestMethod.POST)
    public String updated(@ModelAttribute User user, Model model){
        model.addAttribute("user", user);
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
