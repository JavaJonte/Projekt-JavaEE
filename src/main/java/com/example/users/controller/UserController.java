package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.security.MyUserDetails;
import com.example.users.security.MyUserDetailsService;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    @Autowired
    UserService userService;
    MyUserDetailsService myUserDetailsService;
    MyUserDetails myUserDetails;

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
    @RequestMapping(value = "/c/myAccount", method = RequestMethod.POST) // TODO se över endpointen
    public String saveToDB(@ModelAttribute User user, RedirectAttributes ra, Model model)
    {
        model.addAttribute("user", user);
        try {
            userService.createUser(user);
            ra.addFlashAttribute("message", "User created");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        //User thisUser = userService.findUser(user);
        //model.addAttribute("thisUser", thisUser);
        return "accountManagement";
    }
    // @RequestMapping(value = "/login", method = RequestMethod.GET)
    // public String checkLogin(Model model)
    // {
    //     System.out.println("kan jag se detta");
    //       model.addAttribute("user", new User());
    //        return "login";
    // }
    @RequestMapping(value = "/myAccount", method = RequestMethod.GET) // TODO se över endpointen samt ovanstående metod (går att slå ihop?)
    public String logOn(@ModelAttribute User thisUser, Model model)
    {
        Optional<User> u = userService.getUserByUserName(thisUser.getUserName());
        //User thisUser = userService.findUser(user);
        model.addAttribute("thisUser", u.get());
        return "accountManagement";
    }

    //     @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    //   public String getPassword(Model model){
    //     model.addAttribute("user", new User());
    //     return "forgotPassword";
    //  }
    //  @RequestMapping(value = "/forgotPassword/passRecovery", method = RequestMethod.POST)
    // public String postSent(@ModelAttribute User user, Model model)
    // {
    //    model.addAttribute("user", user);
    //     return "passRecovery";
    //  }


    @GetMapping("/users/updateUser/{id}")
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

    //@RequestMapping(value = "/u/myAccount", method = RequestMethod.POST) // TODO se över endpointen
    //public String updated(@ModelAttribute User user, Model model){
    //   model.addAttribute("user", user);
    //  try{
    //      userService.saveUser(user);
    //  }catch (UserNameExistException e){
    //     System.out.println(e);
    // }

        //User thisUser = userService.findUser(user);
        //model.addAttribute("thisUser", thisUser);

    //   return "accountManagement";
    //}

    // TODO Se över om nedan ska ligga i samma fil eller om den skall flyttas ut..

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "userList";
    }
}
