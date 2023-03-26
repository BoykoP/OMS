package com.softuni.parking.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getIndex(){

        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model, Principal principal){
        model.addAttribute("user_principal",principal);

        return "home";
    }
}
