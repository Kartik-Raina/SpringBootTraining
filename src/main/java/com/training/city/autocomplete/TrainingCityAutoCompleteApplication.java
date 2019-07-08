package com.training.city.autocomplete;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainingCityAutoCompleteApplication {

    public static Set<String> cityNames = new HashSet<>();
    
	public static void main(String[] args) {
		SpringApplication.run(TrainingCityAutoCompleteApplication.class, args);
	}
}
