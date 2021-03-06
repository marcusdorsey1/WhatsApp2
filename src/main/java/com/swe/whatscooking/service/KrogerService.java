package com.swe.whatscooking.service;

import com.swe.whatscooking.dto.RecipeDTO;
import com.swe.whatscooking.entity.Ingredient;
import com.swe.whatscooking.entity.KrogerAPI.KrogerAPIResponse;
import com.swe.whatscooking.entity.KrogerAPI.KrogerClient;
import com.swe.whatscooking.entity.KrogerAPI.KrogerCustomer;
import com.swe.whatscooking.entity.KrogerAPI.KrogerProduct;
import com.swe.whatscooking.entity.Process;
import com.swe.whatscooking.entity.Recipe;
import com.swe.whatscooking.entity.TastyAPI.TastyComponent;
import com.swe.whatscooking.entity.TastyAPI.TastyInstruction;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipe;
import com.swe.whatscooking.entity.TastyAPI.TastySection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class KrogerService {
    private RestTemplate restTemplate;
    private MyRecipeService myRecipeService;
    private TastyAPIService tastyAPIService;
    private RecipeService recipeService;

    @Autowired
    private KrogerClient krogerClient;

    public KrogerService(RestTemplate restTemplate, MyRecipeService myRecipeService, TastyAPIService tastyAPIService, RecipeService recipeService) {
        this.restTemplate = restTemplate;
        this.myRecipeService = myRecipeService;
        this.tastyAPIService = tastyAPIService;
        this.recipeService = recipeService;
    }

    public KrogerCustomer getAccessToken(String code){
        // Token request Headers and Body information
        String clientId = "whatscooking-acf72f4b2dbda6aa726382a398050a863025969198037206606";
        String clientSecret = "5yRrceH3sR53QNA7AfC9JTp6I4KTYdmVCvDyBxxA";
        String body = "grant_type=authorization_code";
        String redirectUri = "https://whats-cooking-app-swe6813.herokuapp.com/callback";
        body = body + "&code=" + code;
        body = body + "&redirect_uri=" + redirectUri;
        // Encoding the Authorization
        byte[] encodedBytes = Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes());
        HttpHeaders responseHeaders = new HttpHeaders();
        // Adding Headers to the request
        responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        responseHeaders.add("Authorization", "Basic " + new String(encodedBytes));
//		System.out.println(responseHeaders.toString());
        // Creating HTTP Request with body and headers established
        HttpEntity<String> request = new HttpEntity<>(body, responseHeaders);
//		System.out.println("Has Body? " + request.hasBody());
//		System.out.println("Here is the body Body? " + request.getBody());
        // Calling Rest Endpoint and getting information into the Kroger Customer Variable
        KrogerCustomer krogerCustomer = restTemplate.postForObject("https://api.kroger.com/v1/connect/oauth2/token",request, KrogerCustomer.class);
        //System.out.println(krogerCustomer.toString());
        return krogerCustomer;
    }

    public void orderProducts(String token, String products){
        // Rest connection to add product into cart
        //TODO: Currently hard coded, but items will be updated to be dynamic
        //String body = "{ \"items\": [{\"upc\": \"0021065600000\",\"quantity\": 1},{\"upc\": \"0001111004970\",\"quantity\": 1},{\"upc\": \"0000000004608\",\"quantity\": 1}]}";
        System.out.println(products);

        // Adding Headers to request
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Authorization", "Bearer " + token);
//		System.out.println(responseHeaders.toString());
        // Creating HTTP Request
        HttpEntity<String> request = new HttpEntity<>(products, responseHeaders);
//      System.out.println(token);
//		System.out.println("Has Body? " + request.hasBody());
//		System.out.println("Here is the body Body? " + request.getBody());
        // Contacting Cart Rest Endpoint using PUT request
        restTemplate.put("https://api.kroger.com/v1/cart/add",request);
        return;
    }

    public String getProductsPayload(Long recipeId, String source){
        RecipeDTO recipeDTO = new RecipeDTO();
        String payLoad = "";
        String upc;
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
            payLoad = "{ \"items\": [";
            for (Ingredient ingredient:recipeDTO.getIngredients()) {
                if(ingredient.getName().length() > 0){
                    upc = getProductUPC(ingredient.getName());
                    if(upc.length()>0){
                        payLoad = payLoad + "{ \"upc\": \"" + upc + "\",\"quantity\": 1},";
                    }
                }
            }
            payLoad = payLoad.substring(0, payLoad.length() - 1);
            payLoad = payLoad + "]}";
            System.out.println(payLoad);
        }catch (Exception e){
            System.out.println(e);
        }
        return payLoad;
    }

    public String getProductUPC(String productName){
        // Adding Headers to request
        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        responseHeaders.setAccept(list);
        responseHeaders.add("Authorization", "Bearer " + krogerClient.getAccess_token());
        HttpEntity<String> request = new HttpEntity<>(responseHeaders);
        // Contacting Product Rest Endpoint using GET request
        ResponseEntity<KrogerAPIResponse> krogerAPIResponse = restTemplate.exchange("https://api.kroger.com/v1/products?filter.term=" +  productName + "&filter.limit=1", HttpMethod.GET, request, KrogerAPIResponse.class);

        if(krogerAPIResponse.getBody().getData().isEmpty()){
            return "";
        }else{
            System.out.println(krogerAPIResponse.getBody().getData().get(0).getUpc());
            return krogerAPIResponse.getBody().getData().get(0).getUpc();
        }
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
