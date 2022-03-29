package com.swe.whatscooking;

import com.swe.whatscooking.entity.KrogerClient;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipe;
import com.swe.whatscooking.entity.TastyAPI.TastyRecipeSearch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
public class WhatsCookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsCookingApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public KrogerClient getClientToken(RestTemplate restTemplate) throws Exception {
		// API Website link https://sv443.net/jokeapi/v2/
		String clientId = "whatscooking-acf72f4b2dbda6aa726382a398050a863025969198037206606";
		String clientSecret = "5yRrceH3sR53QNA7AfC9JTp6I4KTYdmVCvDyBxxA";
		String body = "grant_type=client_credentials&scope=product.compact";
		byte[] encodedBytes = Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		responseHeaders.add("Authorization", "Basic " + new String(encodedBytes));
		HttpEntity<String> request = new HttpEntity<>(body, responseHeaders);

//		System.out.println("Has Body? " + request.hasBody());
//		System.out.println("Here is the body Body? " + request.getBody());

		// Removed line, while we are not using the functionality
		//KrogerClient krogerClient = restTemplate.postForObject("https://api.kroger.com/v1/connect/oauth2/token",request, KrogerClient.class);
		KrogerClient krogerClient = new KrogerClient();
		System.out.println(krogerClient.toString());

		return krogerClient;
	}
	@Bean
	public List<TastyRecipe> getTastyRecipes(RestTemplate restTemplate) throws Exception {
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
				URI.create("https://tasty.p.rapidapi.com/recipes/list?from=0&size=10&tags=under_30_minutes"),HttpMethod.GET, request, TastyRecipeSearch.class);
		//KrogerClient krogerClient = new KrogerClient();
		System.out.println(tastyRecipeSearch.getBody().getResults().get(1).toString());

		return tastyRecipeSearch.getBody().getResults();
	}
}
