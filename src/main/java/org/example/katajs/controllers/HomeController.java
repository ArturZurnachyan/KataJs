package org.example.katajs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/admin")
    public String showIndexPageAdmin() {
        return "adminHome";
    }

    @GetMapping("/user")
    public String showIndexPageUser() {
        return "userHome";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
