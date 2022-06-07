package com.example.se2project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/userdashboard"})

public class UserController {
@GetMapping
    public String viewPage(){
        return "accountPages/userDashboard";
    }
}

