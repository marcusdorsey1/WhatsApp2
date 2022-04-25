package com.swe.whatscooking.service;

import com.swe.whatscooking.dto.IngredientDTO;
import com.swe.whatscooking.dto.ProcessDTO;
import com.swe.whatscooking.entity.Ingredient;
import com.swe.whatscooking.entity.Process;
import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.mapper.IngredientMapper;
import com.swe.whatscooking.mapper.ProcessMapper;
import com.swe.whatscooking.mapper.RecipeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyRecipeService {
    private RecipeMapper recipeMapper;
    private IngredientMapper ingredientMapper;
    private ProcessMapper processMapper;

    public MyRecipeService(RecipeMapper recipeMapper, IngredientMapper ingredientMapper, ProcessMapper processMapper) {
        this.recipeMapper = recipeMapper;
        this.ingredientMapper = ingredientMapper;
        this.processMapper = processMapper;
    }

    public Long insertRecipe(Recipe recipe){
        recipeMapper.insertRecipe(recipe);
        return recipeMapper.selectLastId();
    }

    public void deleteRecipe(Long recipeId){
        recipeMapper.deleteRecipe(recipeId);
    }

    public Long addIngredient(IngredientDTO ingredientDTO){
        return ingredientMapper.insertIngredient(ingredientDTO);
    }

    public void removeIngredients(Long recipeId){
        ingredientMapper.deleteIngredients(recipeId);
    }

    public Long addProcess(ProcessDTO process){
        return processMapper.insertProcess(process);
    }

    public void removeProcesses(Long recipeId){
        processMapper.deleteProcesses(recipeId);
    }

    public List<Recipe> searchForRecipes(String searchWord){
        return recipeMapper.searchForRecipe(searchWord);
    }

    public List<IngredientDTO> searchForIngredientsByRecipeId(Long recipeId){
        return ingredientMapper.selectIngredientsByID(recipeId);
    }
}
