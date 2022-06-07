package com.example.se2project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin"})
public class ViewController {

    @GetMapping({"/1"})
    public String viewadmDPage(){

        return "adminPages/adminDashboard";
    }
@GetMapping
    public String viewOrderPage(){
    return "adminPages/adminTemplate";
    }
}
