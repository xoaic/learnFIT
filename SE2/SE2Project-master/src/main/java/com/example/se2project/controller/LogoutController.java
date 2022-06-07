package com.example.se2project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/logout"})
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("email");
        return "redirect:/login";
    }

}
