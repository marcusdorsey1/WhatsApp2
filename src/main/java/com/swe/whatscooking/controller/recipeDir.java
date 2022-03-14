package com.swe.whatscooking.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipeDir")
public class recipeDir {
    @GetMapping
    public String getrecipeDir(){
        return "recipeDir";
    }
}
