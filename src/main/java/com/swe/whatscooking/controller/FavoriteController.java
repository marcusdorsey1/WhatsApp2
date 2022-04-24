package com.swe.whatscooking.controller;

import com.swe.whatscooking.dto.RecipeDTO;
import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Menu;
import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipe;
import com.swe.whatscooking.service.FavoriteService;
import com.swe.whatscooking.service.RecipeService;
import com.swe.whatscooking.service.TastyAPIService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/my-favorites")
public class FavoriteController {
    private FavoriteService favoriteService;
    private TastyAPIService tastyAPIService;
    private RecipeService recipeService;


    public FavoriteController(FavoriteService favoriteService, TastyAPIService tastyAPIService, RecipeService recipeService) {
        this.favoriteService = favoriteService;
        this.tastyAPIService = tastyAPIService;
        this.recipeService = recipeService;
    }

    @GetMapping()
    public String getFavoriteList(Model model){
        List<Favorite> favorites = favoriteService.getAllFavoriteItems();
        List<RecipeDTO> recipesDTO = new ArrayList<RecipeDTO>();
        RecipeDTO recipeDTO;
        for (Favorite favorite:favorites) {
            try {
                if (favorite.getSource().equals("TastyAPI")) {
                    TastyRecipe tastyRecipe = this.tastyAPIService.getTastyRecipeById(favorite.getRecipe_id());
                    recipeDTO = convertTastyRecipeToRecipeDTO(tastyRecipe);
                    recipeDTO.setSource(favorite.getSource());
                }
                else if(favorite.getSource().equals("Internal")){
                    Recipe internalRecipe = this.recipeService.retrieveRecipeByID(favorite.getRecipe_id());
                    recipeDTO = convertInternalRecipeToRecipeDTO(internalRecipe);
                    recipeDTO.setSource(favorite.getSource());
                }
                else{
                    recipeDTO = new RecipeDTO();
                }
                recipesDTO.add(recipeDTO);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        model.addAttribute("recipes", recipesDTO);
        // Only for the Favorite DB records
        // model.addAttribute("favorites", favorites);
        // return "Favorite";
        return "ViewFavorites";
    }

    @PostMapping("/add")
    public String addToFavoriteList(@ModelAttribute Favorite favorite){
        favoriteService.addFavoriteRecipe(favorite);
        return "redirect:/my-favorites";
    }

    @PostMapping("/delete")
    public String deleteMenuItem(@ModelAttribute Favorite favorite){
        favoriteService.removeFavoriteItem(favorite);
        return "redirect:/my-favorites";
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
