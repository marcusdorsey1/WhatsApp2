package com.swe.whatscooking;

import com.swe.whatscooking.entity.Joke;
import com.swe.whatscooking.entity.KrogerAPIBody;
import com.swe.whatscooking.entity.KrogerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		// API Website link https://sv443.net/jokeapi/v2/

		return args -> {
			byte[] encodedBytes = Base64.getEncoder().encode("whatscooking-acf72f4b2dbda6aa726382a398050a863025969198037206606:5yRrceH3sR53QNA7AfC9JTp6I4KTYdmVCvDyBxxA".getBytes());
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			responseHeaders.add("Authorization", "Basic " + new String(encodedBytes));
			System.out.println(responseHeaders.toString());
			KrogerAPIBody objKAPI = new KrogerAPIBody();
			objKAPI.setData("grant_type=client_credentials&scope=product.compact");
			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			map.add("grant_type", "client_credentials");
			map.add("scope", "product.compact");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, responseHeaders);

//			HttpEntity<> httpEntity = new HttpEntity<>(MediaType.parseMediaType(objKAPI.getData()), responseHeaders);
//			System.out.println(httpEntity.toString());
			System.out.println("Has Body? " + request.hasBody());
			System.out.println("Here is the body Body? " + request.getBody());
			KrogerClient krogerClient = restTemplate.postForObject("https://api.kroger.com/v1/connect/oauth2/token",request, KrogerClient.class);
			log.info(krogerClient.toString());
		};
	}
}
