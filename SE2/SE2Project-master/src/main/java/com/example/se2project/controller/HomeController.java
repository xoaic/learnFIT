package com.example.se2project.controller;

import com.example.se2project.entity.Product;
import com.example.se2project.service.CategoryService;
import com.example.se2project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/products","/"})
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String view1(Model model){
        List<Product> products = productService.findAll();
        if(!products.isEmpty()){
            model.addAttribute("products", products);
            model.addAttribute("allCategory", categoryService.findAll());
        }
        return "homePage";
    }
//    @GetMapping
//    public String viewProducts(){
//        System.out.println("vao viewProduct() method");
//        return "view-productDetail";
//    }
//
//
//    @GetMapping("/{id}")
//    public String gg(){
//        System.out.println("vao gg() method");
//        return "showProduct";
//    }
}
