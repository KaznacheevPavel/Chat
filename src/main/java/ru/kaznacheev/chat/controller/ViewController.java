package ru.kaznacheev.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "/html/login.html";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "/html/registration.html";
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "/html/chat.html";
    }


}
