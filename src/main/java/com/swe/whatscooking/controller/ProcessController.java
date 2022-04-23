package com.swe.whatscooking.controller;

import com.swe.whatscooking.dto.ProcessDTO;
import com.swe.whatscooking.service.MyRecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/processes")
public class ProcessController {
    private MyRecipeService myRecipeService;

    public ProcessController(MyRecipeService myRecipeService) {
        this.myRecipeService = myRecipeService;
    }

    @GetMapping("/add-process")
    public String addIngredientView(@RequestParam("recipeId") Long recipeId, Model model){
        model.addAttribute("recipeId", recipeId);
        return "AddProcedure";
    }

    @PostMapping("/add-process")
    public String newIngredient(@ModelAttribute ProcessDTO process, Model model){
        myRecipeService.addProcess(process);
        Long recipeId = process.getRecipe_id();
        model.addAttribute("recipeId", recipeId);
        return "AddProcedure";
    }
}
