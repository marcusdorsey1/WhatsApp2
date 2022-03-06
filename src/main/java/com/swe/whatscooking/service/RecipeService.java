package com.swe.whatscooking.service;

import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public List<Recipe> retrieveAllRecipes(){
        return (List<Recipe>) recipeRepository.findAll();
    }

}
