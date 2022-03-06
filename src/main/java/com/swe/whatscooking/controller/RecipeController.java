package com.swe.whatscooking.controller;

import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public void setRecipeService(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        List<Recipe> recipes = recipeService.retrieveAllRecipes();
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }
}
