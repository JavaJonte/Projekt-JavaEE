package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.security.MyUserDetails;
import com.example.users.security.MyUserDetailsService;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import java.security.Principal;
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


        try {
            userService.createUser(user);   // Ändra så att denna metod returnerar den skapade usern?
            Optional<User> userCreated = userService.getUserByUserName(user.getUserName());
            model.addAttribute("user", userCreated.get());
            ra.addFlashAttribute("message", "User created");

            return "accountManagement";
        } catch (UserNameExistException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/createAccount";
        }
        //User thisUser = userService.findUser(user);
        //model.addAttribute("thisUser", thisUser);

    }
    // @RequestMapping(value = "/login", method = RequestMethod.GET)
    // public String checkLogin(Model model)
    // {
    //     System.out.println("kan jag se detta");
    //       model.addAttribute("user", new User());
    //        return "login";
    // }
    @RequestMapping(value = "/myAccount", method = RequestMethod.GET) // TODO se över endpointen samt ovanstående metod (går att slå ihop?)
    public String logOn(@ModelAttribute User user, Model model, HttpServletRequest request)
    {
        Principal principal = request.getUserPrincipal();
        System.out.println("SNÄLLLLLA" + principal.getName());
        Optional<User> u = userService.getUserByUserName(principal.getName());
        if(u.isPresent()){

            if(u.get().getRoles().equals("ROLE_ADMIN")){
                System.out.println("Hej hej , du har full behörighet!");
                return "redirect:/users";
            }
            else {
                model.addAttribute("user", u.get());
                return "accountManagement";
            }

        }else {
            return "redirect:/login";
        }
        //User thisUser = userService.findUser(user);



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
        System.out.println("printa ut user gagagaga:" + user.toString());
        try {
            userService.updateUser(user);
            ra.addFlashAttribute("message", "Användaren har uppdaterats");
        } catch (UserNameExistException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
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
    // ADMIN INLOGGAD

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "userList";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        userService.delete(id);
        ra.addFlashAttribute("message", "Anvädaren med ID: " + id + " har raderats");

        return "redirect:/users";
    }


}
