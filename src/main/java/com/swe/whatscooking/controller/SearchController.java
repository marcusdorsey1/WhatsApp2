package com.swe.whatscooking.controller;

import com.swe.whatscooking.dto.RecipeDTO;
import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipe;
import com.swe.whatscooking.service.MyRecipeService;
import com.swe.whatscooking.service.TastyAPIService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private TastyAPIService tastyAPIService;
    private MyRecipeService myRecipeService;

    public SearchController(TastyAPIService tastyAPIService, MyRecipeService myRecipeService) {
        this.tastyAPIService = tastyAPIService;
        this.myRecipeService = myRecipeService;
    }

    @GetMapping
    public String getSearchPage(){
        return "SearchForm";
    }

    @GetMapping("/results")
    public String getSearchResults(@RequestParam("searchWord") String searchWord, Model model){
        List<RecipeDTO> recipesDTO = new ArrayList<RecipeDTO>();
        RecipeDTO recipeDTO;
        List<Recipe> recipes = myRecipeService.searchForRecipes(searchWord);
        for (Recipe recipe:recipes) {
            recipeDTO = convertInternalRecipeToRecipeDTO(recipe);
            recipeDTO.setSource("Internal");
            recipesDTO.add(recipeDTO);
        }
        try {
            List<TastyRecipe> tastyRecipesByTag = tastyAPIService.getTastyRecipesByTag(searchWord);
            for (TastyRecipe tastyRecipe:tastyRecipesByTag) {
                recipeDTO = convertTastyRecipeToRecipeDTO(tastyRecipe);
                recipeDTO.setSource("TastyAPI");
                recipesDTO.add(recipeDTO);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            List<TastyRecipe> tastyRecipesByQ = tastyAPIService.getTastyRecipesByQ(searchWord);
            for (TastyRecipe tastyRecipe:tastyRecipesByQ) {
                recipeDTO = convertTastyRecipeToRecipeDTO(tastyRecipe);
                recipeDTO.setSource("TastyAPI");
                recipesDTO.add(recipeDTO);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        model.addAttribute("recipes", recipesDTO);
        return "ViewAllSearchedRecipes";
    }

    private RecipeDTO convertInternalRecipeToRecipeDTO(Recipe recipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        BeanUtils.copyProperties(recipe, recipeDTO);
        return recipeDTO;
    }

    private RecipeDTO convertTastyRecipeToRecipeDTO(TastyRecipe tastyRecipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        BeanUtils.copyProperties(tastyRecipe, recipeDTO);
        return recipeDTO;
    }
}
