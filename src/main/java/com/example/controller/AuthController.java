package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    // ユーザ作成画面表示
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // ユーザ作成処理
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        try {
            userService.registerUser(username, password, role);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }

        //userService.registerUser(username, password, role);
        return "redirect:/login";
    }

    // ログイン画面表示
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

}