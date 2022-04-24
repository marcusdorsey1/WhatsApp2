package com.swe.whatscooking.controller;
import com.swe.whatscooking.dto.RecipeDTO;
import com.swe.whatscooking.entity.Ingredient;
import com.swe.whatscooking.entity.Process;
import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.entity.TastyAPI.TastyComponent;
import com.swe.whatscooking.entity.TastyAPI.TastyInstruction;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipe;
import com.swe.whatscooking.entity.TastyAPI.TastySection;
import com.swe.whatscooking.service.RecipeService;
import com.swe.whatscooking.service.TastyAPIService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recipeDir")
public class recipeDir {
    private TastyAPIService tastyAPIService;
    private RecipeService recipeService;

    public recipeDir(TastyAPIService tastyAPIService, RecipeService recipeService) {
        this.tastyAPIService = tastyAPIService;
        this.recipeService = recipeService;
    }
    /*@GetMapping
    public String getRecipeDir(){
        return "recipeDir";
    }*/

    @GetMapping
    public String getRecipeDir(@RequestParam("recipeId") Long recipeId, @RequestParam("source") String source, Model model){
        System.out.println(recipeId);
        System.out.println(source);
        RecipeDTO recipeDTO;
        try {
            if (source.equals("TastyAPI")) {
                TastyRecipe tastyRecipe = this.tastyAPIService.getTastyRecipeById(recipeId);
                recipeDTO = convertTastyRecipeToRecipeDTO(tastyRecipe);
                recipeDTO.setSource("TastyAPI");
            }
            else if(source.equals("Internal")){
                Recipe internalRecipe = this.recipeService.retrieveRecipeByID(recipeId);
                recipeDTO = convertInternalRecipeToRecipeDTO(internalRecipe);
                recipeDTO.setSource("Internal");
            }
            else{
                recipeDTO = new RecipeDTO();
            }
            model.addAttribute("recipe", recipeDTO);
            System.out.println(recipeDTO.getName() + recipeDTO.getImage());
        }catch (Exception e){
            System.out.println(e);
        }

        return "recipeDir";
    }


    private RecipeDTO convertTastyRecipeToRecipeDTO(TastyRecipe tastyRecipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        List<Process> processes = new ArrayList<Process>();

        for (TastyInstruction tastyInstruction:tastyRecipe.getInstructions()) {
            processes.add(new Process(tastyInstruction.getDisplay_text(), tastyInstruction.getPosition()));
        }

        for (TastySection tastySection:tastyRecipe.getSections()) {
            for(TastyComponent tastyComponent: tastySection.getComponents()){
                ingredients.add(new Ingredient(tastyComponent.getIngredient().getName(), tastyComponent.getRaw_text()));
            }
        }
        BeanUtils.copyProperties(tastyRecipe, recipeDTO);
        recipeDTO.setProcesses(processes);
        recipeDTO.setIngredients(ingredients);
        return recipeDTO;
    }

    private RecipeDTO convertInternalRecipeToRecipeDTO(Recipe recipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        BeanUtils.copyProperties(recipe, recipeDTO);
        return recipeDTO;
    }
}
