package com.spring.rest.field.validate.api;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringRestFieldValidationApplication {

	@PostMapping("/api/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user, Errors errors) {
		Response response = new Response();
		// If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {
			// get all errors
			response.setMessage(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		} else {
			response.setMessage("User inserted with Name " + user.getName());
		}
		return ResponseEntity.badRequest().body(response);

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRestFieldValidationApplication.class, args);
	}
}
