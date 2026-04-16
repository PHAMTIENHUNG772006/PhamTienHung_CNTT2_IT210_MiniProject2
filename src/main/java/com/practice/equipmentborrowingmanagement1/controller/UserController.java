package com.practice.equipmentborrowingmanagement1.controller;

import com.practice.equipmentborrowingmanagement1.customException.EmailException;
import com.practice.equipmentborrowingmanagement1.model.entity.User;
import com.practice.equipmentborrowingmanagement1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("UserController")
@RequestMapping({"/","/user"})
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (EmailException e) {
            result.rejectValue("email", "error.user", e.getMessage());

            return "register";
        }
    }


    @GetMapping("login")
    public String login(

            Model model
    ){



        return "login";
    }



    @GetMapping("login")
    public String doLogin(
            Model model
    ){


        return "login";
    }




}
