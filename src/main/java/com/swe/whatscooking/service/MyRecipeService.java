package com.swe.whatscooking.service;

import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.mapper.RecipeMapper;
import org.springframework.stereotype.Service;

@Service
public class MyRecipeService {
    private RecipeMapper recipeMapper;

    public MyRecipeService(RecipeMapper recipeMapper) {
        this.recipeMapper = recipeMapper;
    }

    public Integer insertRecipe(Recipe recipe){
        return recipeMapper.insertRecipe(recipe);
    }

    public void deleteRecipe(Long recipeId){
        recipeMapper.deleteRecipe(recipeId);
    }
}
