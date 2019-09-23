package com.rjgonzalez.insurances.arquitecture.challenge;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Client Entity
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@SpringBootApplication
public class InsurancesArquitectureChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurancesArquitectureChallengeApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
