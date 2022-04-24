package com.swe.whatscooking.service;

import com.swe.whatscooking.entity.TastyAPI.TastyRecipe;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipeSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class TastyAPIService {
    @Autowired
    private RestTemplate restTemplate;

    public List<TastyRecipe> getTastyRecipesByTag(String searchWord) throws Exception {
        // API Website link https://sv443.net/jokeapi/v2/
        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        responseHeaders.setAccept(list);
        responseHeaders.add("X-RapidAPI-Host", "tasty.p.rapidapi.com");
        responseHeaders.add("X-RapidAPI-Key", "577422b5c7mshcf41f00911ef268p1b79f5jsn812ae4218dff");
        HttpEntity<String> request = new HttpEntity<String>(responseHeaders);

        // Removed line, while we are not using the functionality
        ResponseEntity<TastyRecipeSearch> tastyRecipeSearch = restTemplate.exchange(
                URI.create("https://tasty.p.rapidapi.com/recipes/list?from=0&size=5&tags=" + searchWord),HttpMethod.GET, request, TastyRecipeSearch.class);
        //KrogerClient krogerClient = new KrogerClient();
        System.out.println(tastyRecipeSearch.getBody().getResults().get(1).toString());

        return tastyRecipeSearch.getBody().getResults();
    }

    public List<TastyRecipe> getTastyRecipesByQ(String searchWord) throws Exception {
        // API Website link https://sv443.net/jokeapi/v2/
        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        responseHeaders.setAccept(list);
        responseHeaders.add("X-RapidAPI-Host", "tasty.p.rapidapi.com");
        responseHeaders.add("X-RapidAPI-Key", "577422b5c7mshcf41f00911ef268p1b79f5jsn812ae4218dff");
        HttpEntity<String> request = new HttpEntity<String>(responseHeaders);

        // Removed line, while we are not using the functionality
        ResponseEntity<TastyRecipeSearch> tastyRecipeSearch = restTemplate.exchange(
                URI.create("https://tasty.p.rapidapi.com/recipes/list?from=0&size=5&q=" + searchWord), HttpMethod.GET, request, TastyRecipeSearch.class);
        //KrogerClient krogerClient = new KrogerClient();
        System.out.println(tastyRecipeSearch.getBody().getResults().get(1).toString());

        return tastyRecipeSearch.getBody().getResults();
    }

    public TastyRecipe getTastyRecipeById(Long id) throws Exception {
        // API Website link https://sv443.net/jokeapi/v2/
        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        responseHeaders.setAccept(list);
        responseHeaders.add("X-RapidAPI-Host", "tasty.p.rapidapi.com");
        responseHeaders.add("X-RapidAPI-Key", "577422b5c7mshcf41f00911ef268p1b79f5jsn812ae4218dff");
        HttpEntity<String> request = new HttpEntity<String>(responseHeaders);

        // Removed line, while we are not using the functionality
        ResponseEntity<TastyRecipe> tastyRecipe = restTemplate.exchange(
                URI.create("https://tasty.p.rapidapi.com/recipes/get-more-info?id=" + id),HttpMethod.GET, request, TastyRecipe.class);
        /*System.out.println("Printing out the Recipe");
        System.out.println(tastyRecipe.getBody().toString());
        System.out.println("Printing out the instructions of the Recipe");
        System.out.println(tastyRecipe.getBody().getInstructions());
        System.out.println("Printing out the selection of the Recipe");
        System.out.println(tastyRecipe.getBody().getSections());
        System.out.println("Printing out one selection components of the Recipe");
        System.out.println(tastyRecipe.getBody().getSections().get(1).getComponents());
        System.out.println("Printing out one of the components");
        System.out.println(tastyRecipe.getBody().getSections().get(1).getComponents().get(1).getIngredient());
        System.out.println("Printing out ingredient of selection");
        System.out.println(tastyRecipe.getBody().getSections().get(1).getComponents().get(1).getIngredient());*/

        return tastyRecipe.getBody();
    }
}
