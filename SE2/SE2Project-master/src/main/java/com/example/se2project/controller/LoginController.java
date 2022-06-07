package com.example.se2project.controller;

import com.example.se2project.entity.User;
import com.example.se2project.entity.dto.LoginRequestDto;
import com.example.se2project.service.AuthService;
import com.example.se2project.util.LogFactory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping({"/login"})
@SessionAttributes({"email", "userId"})
public class LoginController {

    @Autowired
    private AuthService authService;
    private final Logger LOGGER = LogFactory.getLogger();

    @GetMapping
    public String login(Model model){
        model.addAttribute("loginRequestDto", new LoginRequestDto());
        return "adminPages/users/loginPage";
    }

    @PostMapping
    public String checkLogin(@ModelAttribute @Valid LoginRequestDto loginRequestDto,
                             BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()){
            return "adminPages/users/loginPage";
        }

        User user = authService.existedUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if(Objects.isNull(user)){
            ObjectError objectError = new ObjectError("error", "Invalid email or password!");
            bindingResult.addError(objectError);
            return "adminPages/users/loginPage";
        }

        model.addAttribute("userId", user.getUserId());
        model.addAttribute("email", user.getEmail());

        LOGGER.info("Logged successfully!");

        return "redirect:/";
    }
}
