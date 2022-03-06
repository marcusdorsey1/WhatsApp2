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
public class WhatscookingApplication {

	private static final Logger log = LoggerFactory.getLogger(WhatscookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WhatscookingApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Joke joke = restTemplate.getForObject(
					"https://v2.jokeapi.dev/joke/Pun", Joke.class);
			log.info(joke.toString());
		};
	}


}
