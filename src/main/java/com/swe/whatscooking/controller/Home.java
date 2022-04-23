package com.swe.whatscooking.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.swe.whatscooking.dto.RecipeDTO;
import com.swe.whatscooking.entity.Ingredient;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipe;
import com.swe.whatscooking.service.TastyAPIService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/home")
public class Home {
    private TastyAPIService tastyAPIService;

    public Home(TastyAPIService tastyAPIService) {
        this.tastyAPIService = tastyAPIService;
    }

    @GetMapping
    public String getHomePage(Model model){
        /*List<RecipeDTO> recipesDTO = new ArrayList<RecipeDTO>();
        try {
            List<TastyRecipe> tastyRecipes = tastyAPIService.getTastyRecipes();
            for (TastyRecipe tastyRecipe:tastyRecipes) {
                recipesDTO.add(convertTastyRecipeToRecipeDTO(tastyRecipe));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        model.addAttribute("recipes", recipesDTO);*/
        return "weeklyView";
    }

    private RecipeDTO convertTastyRecipeToRecipeDTO(TastyRecipe tastyRecipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        BeanUtils.copyProperties(tastyRecipe, recipeDTO);
        return recipeDTO;
    }
}
