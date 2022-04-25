package com.swe.whatscooking.internal;

import com.swe.whatscooking.dto.IngredientDTO;
import com.swe.whatscooking.dto.ProcessDTO;
import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Ingredient;
import com.swe.whatscooking.entity.Menu;
import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.service.FavoriteService;
import com.swe.whatscooking.service.MenuService;
import com.swe.whatscooking.service.MyRecipeService;
import com.swe.whatscooking.service.RecipeService;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppServicesTest {
    @LocalServerPort
    private int port;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private MyRecipeService myRecipeService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private FavoriteService favoriteService;

    @Test
    public void testMyRecipeService() {
        // Testing getting all items function
        List<Recipe> recipes = recipeService.retrieveAllRecipes();
        assertThat(recipes.size(), equalTo(3));
        // Testing adding item function
        Recipe testRecipe = new Recipe();
        testRecipe.setName("Test Recipe");
        myRecipeService.insertRecipe(testRecipe);
        recipes = recipeService.retrieveAllRecipes();
        assertThat(recipes.size(), equalTo(4));
        // Testing adding Ingredients and Process to new recipe
        IngredientDTO testIngredient = new IngredientDTO();
        testIngredient.setName("Water");
        testIngredient.setRecipe_id(4L);
        testIngredient.setQuantity(4);
        testIngredient.setMeasurement("oz");
        ProcessDTO testProcess = new ProcessDTO();
        testProcess.setRecipe_id(4L);
        testProcess.setDescription("Heat pan to a boil");
        testProcess.setStep(1);
        Long insertResult = myRecipeService.addIngredient(testIngredient);
        assertThat(insertResult, equalTo(1L));
        insertResult = myRecipeService.addProcess(testProcess);
        assertThat(insertResult, equalTo(1L));
        // Testing deleting item function
        myRecipeService.removeIngredients(4L);
        myRecipeService.removeProcesses(4L);
        myRecipeService.deleteRecipe(4L);
        recipes = recipeService.retrieveAllRecipes();
        assertThat(recipes.size(), equalTo(3));
    }

    @Test
    public void testMenuService() {
        // Testing getting all items function
        List<Menu> menus = menuService.getAllMenuItems();
        assertThat(menus.size(), equalTo(2));
        // Testing deleting item function
        menuService.removeMenuItem(menus.get(0));
        menus = menuService.getAllMenuItems();
        assertThat(menus.size(), equalTo(1));
        // Testing adding item function
        Menu testMenu = new Menu();
        testMenu.setRecipe_id(3L);
        testMenu.setSource("Internal");
        menuService.addMenuItem(testMenu);
        menus = menuService.getAllMenuItems();
        assertThat(menus.size(), equalTo(2));
    }

    @Test
    public void testFavoriteService() {
        // Testing getting all items function
        List<Favorite> favorites = favoriteService.getAllFavoriteItems();
        assertThat(favorites.size(), equalTo(1));
        // Testing deleting item function
        favoriteService.removeFavoriteItem(favorites.get(0));
        favorites = favoriteService.getAllFavoriteItems();
        assertThat(favorites.size(), equalTo(0));
        Favorite testFavorite = new Favorite();
        testFavorite.setRecipe_id(3L);
        testFavorite.setSource("Internal");
        // Testing adding item function
        favoriteService.addFavoriteRecipe(testFavorite);
        favorites = favoriteService.getAllFavoriteItems();
        assertThat(favorites.size(), equalTo(1));
    }

}
