package com.swe.whatscooking;

import com.swe.whatscooking.entity.KrogerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@SpringBootApplication
public class WhatsCookingApplication {

	private static final Logger log = LoggerFactory.getLogger(WhatsCookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WhatsCookingApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
/*
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		// API Website link https://sv443.net/jokeapi/v2/
		return args -> {
			Joke joke = restTemplate.getForObject(
					"https://v2.jokeapi.dev/joke/Pun", Joke.class);
			log.info(joke.toString());
		};
	}
*/
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
//		System.out.println(responseHeaders.toString());
		HttpEntity<String> request = new HttpEntity<>(body, responseHeaders);

//		System.out.println("Has Body? " + request.hasBody());
//		System.out.println("Here is the body Body? " + request.getBody());
		KrogerClient krogerClient = restTemplate.postForObject("https://api.kroger.com/v1/connect/oauth2/token",request, KrogerClient.class);
		log.info(krogerClient.toString());

		return krogerClient;
	}

}
