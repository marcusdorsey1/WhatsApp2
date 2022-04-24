package com.swe.whatscooking.controller;

import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Menu;
import com.swe.whatscooking.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/my-menu")
public class MenuController {
    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping()
    public String getMenuList(Model model){
        List<Menu> menus = menuService.getAllMenuItems();
        model.addAttribute("menus", menus);
        return "Menu";
    }

    @PostMapping("/add")
    public String addToMenuList(@ModelAttribute Menu menu){
        menuService.addMenuItem(menu);
        return "redirect:/my-menu";
    }

}
