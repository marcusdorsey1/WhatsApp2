package com.swe.whatscooking.controller;

import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//TODO: Build better REST API
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

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id){
        Recipe recipe = recipeService.retrieveRecipeByID(id);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }
    @GetMapping("/recipes/cuisine")
    public ResponseEntity<List<Recipe>> getRecipeByCuisine(@RequestParam(name="cuisine") String cuisine){
        List<Recipe> recipes = recipeService.retrieveRecipeByCuisine(cuisine);
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }
    @GetMapping("/recipes/name")
    public ResponseEntity<List<Recipe>> getRecipeByName(@RequestParam(name="name") String name){
        List<Recipe> recipes = recipeService.retrieveRecipeByName(name);
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }
}
