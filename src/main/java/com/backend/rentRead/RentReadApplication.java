package com.backend.rentRead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RentReadApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentReadApplication.class, args);
	}
}
