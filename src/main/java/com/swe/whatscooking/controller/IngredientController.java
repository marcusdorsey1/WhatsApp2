package com.swe.whatscooking.controller;

import com.swe.whatscooking.dto.IngredientDTO;
import com.swe.whatscooking.service.MyRecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private MyRecipeService myRecipeService;

    public IngredientController(MyRecipeService myRecipeService) {
        this.myRecipeService = myRecipeService;
    }

    @GetMapping("/add-ingredient")
    public String addIngredientView(){
        return "AddIngredient";
    }

    @PostMapping("/add-ingredient")
    public String newIngredient(@ModelAttribute IngredientDTO ingredientDTO, Model model){
        System.out.println(ingredientDTO.toString());
        Long recipeId = ingredientDTO.getRecipe_id();
        System.out.println(recipeId);
        myRecipeService.addIngredient(ingredientDTO);
        model.addAttribute("recipeId", recipeId);
        return "AddIngredient";
    }
}
