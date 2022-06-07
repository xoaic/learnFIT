package com.example.se2project.controller;

import com.example.se2project.entity.User;
import com.example.se2project.entity.dto.LoginRequestDto;
import com.example.se2project.entity.dto.RegisterRequestDto;
import com.example.se2project.exception.CustomException;
import com.example.se2project.service.UserService;
import com.example.se2project.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping({"/register"})
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registerView(Model model) {
        model.addAttribute("registerRequestDto", new RegisterRequestDto());
        return "registerPage";
    }

    @PostMapping
    public String registerNewUser(@ModelAttribute @Valid RegisterRequestDto registerRequestDto,
                                  BindingResult bindingResult,
                                  Model model) throws CustomException {
        if (bindingResult.hasErrors()) {
            return "registerPage";
        }

        if (userService.isExistedEmail(registerRequestDto.getEmail())) {
            bindingResult.rejectValue("email", "error.registerRequestDto", "This email is existed!");
            return "registerPage";
        }

        User newUser = ConvertUtils.convertDtoToEntity(registerRequestDto, User.class);
        userService.insert(newUser);

        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .build();
        model.addAttribute("loginRequestDto", loginRequestDto);
        return "loginPage";
    }
}
