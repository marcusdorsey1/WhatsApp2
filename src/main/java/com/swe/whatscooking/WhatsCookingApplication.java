package com.swe.whatscooking;

import com.swe.whatscooking.entity.Joke;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		// API Website link https://sv443.net/jokeapi/v2/
		return args -> {
			Joke joke = restTemplate.getForObject(
					"https://v2.jokeapi.dev/joke/Pun", Joke.class);
			log.info(joke.toString());
		};
	}


}
