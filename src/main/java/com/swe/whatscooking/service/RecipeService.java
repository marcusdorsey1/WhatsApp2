package com.swe.whatscooking.service;

import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public List<Recipe> retrieveAllRecipes(){
        return (List<Recipe>) recipeRepository.findAll();
    }

    public Recipe retrieveRecipeByID(Long id){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        Recipe recipe = recipeOptional.orElseThrow(RecipeNotFoundException::new);
        return recipe;
    }
    public  List<Recipe> retrieveRecipeByName(String name){
        List<Recipe> recipes = recipeRepository.findByName(name);
        return recipes;
    }

    public  List<Recipe> retrieveRecipeByCuisine(String cuisine){
        List<Recipe> recipes = recipeRepository.findByCuisine(cuisine);
        return recipes;
    }

}
