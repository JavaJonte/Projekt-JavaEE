package com.example.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {


    @RequestMapping("/")
    public String getIndex(@RequestParam(value = "id", required = false) Integer id, Model model) {

        System.out.println("SER VI DETTA I LOGGEN?");
        model.addAttribute("id", id);
        return "index";
    }


}
