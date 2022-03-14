package com.swe.whatscooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Login")
public class Login {
    @GetMapping
    public String getLoginPage(){
        return "Login";
    }
}
