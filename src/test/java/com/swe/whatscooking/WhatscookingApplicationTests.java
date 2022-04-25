package com.swe.whatscooking;

import com.swe.whatscooking.dto.IngredientDTO;
import com.swe.whatscooking.dto.ProcessDTO;
import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Menu;
import com.swe.whatscooking.service.FavoriteService;
import com.swe.whatscooking.service.MenuService;
import com.swe.whatscooking.service.MyRecipeService;
import org.junit.jupiter.api.Test;

import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.service.RecipeService;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WhatscookingApplicationTests {

	@Test
	void contextLoads() {
	}

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private MyRecipeService myRecipeService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private FavoriteService favoriteService;

	@Test
	public void getAllRecipesNoAccess() {
		ResponseEntity<List> response =
				this.restTemplate.getForEntity("http://localhost:" + port + "/recipes", List.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
	}

	@Test
	public void getRecipeNoAccess() {
		ResponseEntity<Recipe> response =
				this.restTemplate.getForEntity("http://localhost:" + port + "/recipes/1", Recipe.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
	}

	@Test
	public void getRecipeByCuisineNoAccess() {
		ResponseEntity<Recipe> response =
				this.restTemplate.getForEntity("http://localhost:" + port + "/recipes/cuisine", Recipe.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
	}

	@Test
	public void getRecipeByNameNoAccess() {
		ResponseEntity<Recipe> response =
				this.restTemplate.getForEntity("http://localhost:" + port + "/recipes/name", Recipe.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
	}

	@Test
	public void getAllRecipes() {
		// Calling API
		ResponseEntity<List> response = restTemplate.exchange(
				URI.create("http://localhost:" + port + "/recipes"), HttpMethod.GET, getHttpHeaders(), List.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().isEmpty(), equalTo(false));
	}

	@Test
	public void getRecipeByID() {
		Recipe recipe = recipeService.retrieveRecipeByID(1L);
		ResponseEntity<Recipe> response =
				this.restTemplate.exchange("http://localhost:" + port + "/recipes/1", HttpMethod.GET, getHttpHeaders(), Recipe.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertEquals(response.getBody().getId(), recipe.getId());
		assertEquals(response.getBody().getName(), recipe.getName());

		ResponseEntity<String> response2 =
				this.restTemplate.exchange("http://localhost:" + port + "/recipes/0", HttpMethod.GET, getHttpHeaders(), String.class);

		assertThat(response2.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		assertEquals(true, response2.hasBody());
		assertEquals(404, response2.getStatusCodeValue());
	}

	@Test
	public void getRecipeByCuisine() {
		List<Recipe> recipes = recipeService.retrieveRecipeByCuisine("Latin");
		ResponseEntity<List> response =
				this.restTemplate.exchange("http://localhost:" + port + "/recipes/cuisine?cuisine=Latin", HttpMethod.GET, getHttpHeaders(), List.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().isEmpty(), equalTo(false));

		ResponseEntity<List> response2 =
				this.restTemplate.exchange("http://localhost:" + port + "/recipes/cuisine?cuisine=Brazilian", HttpMethod.GET, getHttpHeaders(), List.class);

		assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response2.getBody().isEmpty(), equalTo(true));
	}

	@Test
	public void getRecipeByName() {
		ResponseEntity<List> response =
				this.restTemplate.exchange("http://localhost:" + port + "/recipes/name?name=Pizza", HttpMethod.GET, getHttpHeaders(), List.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().isEmpty(), equalTo(false));

		ResponseEntity<List> response2 =
				this.restTemplate.exchange("http://localhost:" + port + "/recipes/name?name=Tofu", HttpMethod.GET, getHttpHeaders(), List.class);

		assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response2.getBody().isEmpty(), equalTo(true));
	}

	private static HttpEntity getHttpHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		List<MediaType> list = new ArrayList<>();
		list.add(MediaType.APPLICATION_JSON);
		responseHeaders.setAccept(list);
		responseHeaders.setBasicAuth("user", "password");
		HttpEntity<String> request = new HttpEntity<String>(responseHeaders);
		return request;
	}

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
