package com.swe.whatscooking.rest;

import com.swe.whatscooking.entity.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RecipeControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("cuisine", "Latin");
        ResponseEntity<Recipe> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/recipes/cuisine", Recipe.class, uriVariables);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

    @Test
    public void getRecipeByNameNoAccess() {
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("name", "Pizza");
        ResponseEntity<Recipe> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/recipes/name", Recipe.class, uriVariables);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

}
