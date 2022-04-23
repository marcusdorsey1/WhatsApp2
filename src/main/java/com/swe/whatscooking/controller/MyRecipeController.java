package com.swe.whatscooking.controller;

import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.service.MyRecipeService;
import com.swe.whatscooking.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/my-recipes")
public class MyRecipeController {
    private RecipeService recipeService;
    private MyRecipeService myRecipeService;

    public MyRecipeController(RecipeService recipeService, MyRecipeService myRecipeService) {
        this.recipeService = recipeService;
        this.myRecipeService = myRecipeService;
    }

    @GetMapping()
    public String getRegistrationForm(Model model){
        List<Recipe> recipes = recipeService.retrieveAllRecipes();
        model.addAttribute("recipes", recipes);
        return "ViewAllRecipes";
    }

    @GetMapping("/create-recipe")
    public String createRecipeView(){
        return "createRecipe";
    }


    @PostMapping("/create-recipe")
    public String newRecipe(@ModelAttribute Recipe recipe, Model model){
        Long recipeId = myRecipeService.insertRecipe(recipe);
        System.out.println("My New recipe ID is:" + recipeId);
        model.addAttribute("recipeId", recipeId);
        return "AddIngredient";
    }

    @PostMapping("/delete-recipe")
    public String delRecipe(@ModelAttribute Recipe recipe, Model model){
        myRecipeService.removeIngredients(recipe.getId());
        myRecipeService.removeProcesses(recipe.getId());
        myRecipeService.deleteRecipe(recipe.getId());
        return "redirect:/my-recipes";
    }

}
