package com.swe.whatscooking.rest;

import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.service.RecipeService;
import org.junit.Test;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RecipeControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RecipeService recipeService;

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
                URI.create("http://localhost:" + port + "/recipes"),HttpMethod.GET, getHttpHeaders(), List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().isEmpty(), equalTo(false));
    }

    @Test
    public void getRecipeByID() {
        Recipe recipe = recipeService.retrieveRecipeByID(1L);
        ResponseEntity<Recipe> response =
                this.restTemplate.exchange("http://localhost:" + port + "/recipes/1",HttpMethod.GET, getHttpHeaders(), Recipe.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(response.getBody().getId(),recipe.getId());
        assertEquals(response.getBody().getName(),recipe.getName());

        ResponseEntity<String> response2 =
                this.restTemplate.exchange("http://localhost:" + port + "/recipes/0",HttpMethod.GET, getHttpHeaders(), String.class);

        assertThat(response2.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertEquals(true, response2.hasBody());
        assertEquals(404, response2.getStatusCodeValue());
    }

    @Test
    public void getRecipeByCuisine() {
        List<Recipe> recipes = recipeService.retrieveRecipeByCuisine("Latin");
        ResponseEntity<List> response =
                this.restTemplate.exchange("http://localhost:" + port + "/recipes/cuisine?cuisine=Latin",HttpMethod.GET, getHttpHeaders(), List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().isEmpty(), equalTo(false));

        ResponseEntity<List> response2 =
                this.restTemplate.exchange("http://localhost:" + port + "/recipes/cuisine?cuisine=Brazilian",HttpMethod.GET, getHttpHeaders(), List.class);

        assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response2.getBody().isEmpty(), equalTo(true));
    }

    @Test
    public void getRecipeByName() {
        ResponseEntity<List> response =
                this.restTemplate.exchange("http://localhost:" + port + "/recipes/name?name=Pizza",HttpMethod.GET, getHttpHeaders(), List.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().isEmpty(), equalTo(false));

        ResponseEntity<List> response2 =
                this.restTemplate.exchange("http://localhost:" + port + "/recipes/name?name=Tofu",HttpMethod.GET, getHttpHeaders(), List.class);

        assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response2.getBody().isEmpty(), equalTo(true));
    }

    private static HttpEntity getHttpHeaders(){
        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        responseHeaders.setAccept(list);
        responseHeaders.setBasicAuth("user", "password");
        HttpEntity<String> request = new HttpEntity<String>(responseHeaders);
        return request;
    }

}
