package com.swe.whatscooking.controller;

import com.swe.whatscooking.dto.RecipeDTO;
import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Menu;
import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipe;
import com.swe.whatscooking.service.MenuService;
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
@RequestMapping("/my-menu")
public class MenuController {
    private MenuService menuService;
    private TastyAPIService tastyAPIService;
    private RecipeService recipeService;


    public MenuController(MenuService menuService, TastyAPIService tastyAPIService, RecipeService recipeService) {
        this.menuService = menuService;
        this.tastyAPIService = tastyAPIService;
        this.recipeService = recipeService;
    }

    @GetMapping()
    public String getMenuList(Model model){
        List<Menu> menus = menuService.getAllMenuItems();
        List<RecipeDTO> recipesDTO = new ArrayList<RecipeDTO>();
        RecipeDTO recipeDTO;
        for (Menu menu:menus) {
            try {
                if (menu.getSource().equals("TastyAPI")) {
                    TastyRecipe tastyRecipe = this.tastyAPIService.getTastyRecipeById(menu.getRecipe_id());
                    recipeDTO = convertTastyRecipeToRecipeDTO(tastyRecipe);
                    recipeDTO.setSource(menu.getSource());
                }
                else if(menu.getSource().equals("Internal")){
                    Recipe internalRecipe = this.recipeService.retrieveRecipeByID(menu.getRecipe_id());
                    recipeDTO = convertInternalRecipeToRecipeDTO(internalRecipe);
                    recipeDTO.setSource(menu.getSource());
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
        // Only for the Menu view
        // model.addAttribute("menus", menus);
        // return "Menu";
        return "weeklyView";
    }

    @PostMapping("/add")
    public String addToMenuList(@ModelAttribute Menu menu){
        menuService.addMenuItem(menu);
        return "redirect:/my-menu";
    }

    @PostMapping("/delete")
    public String deleteMenuItem(@ModelAttribute Menu menu){
        menuService.removeMenuItem(menu);
        return "redirect:/my-menu";
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
