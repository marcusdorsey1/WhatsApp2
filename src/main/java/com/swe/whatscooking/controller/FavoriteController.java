package com.swe.whatscooking.controller;

import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Menu;
import com.swe.whatscooking.service.FavoriteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/my-favorites")
public class FavoriteController {
    private FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping()
    public String getFavoriteList(Model model){
        List<Favorite> favorites = favoriteService.getAllFavoriteItems();
        model.addAttribute("favorites", favorites);
        return "Favorite";
    }

    @PostMapping("/add")
    public String addToFavoriteList(@ModelAttribute Favorite favorite){
        favoriteService.addFavoriteRecipe(favorite);
        return "redirect:/my-favorites";
    }
}
