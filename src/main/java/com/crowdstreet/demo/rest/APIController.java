package com.crowdstreet.demo.rest;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crowdstreet.demo.data.model.Counts;
import com.crowdstreet.demo.data.model.Request;
import com.crowdstreet.demo.service.CountService;

@RestController
public class APIController {
	
	@Autowired
	CountService countService;

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@PostMapping(path="/request",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.TEXT_PLAIN_VALUE
	)
	public String postRequest(@RequestBody Request body){

		return "";
	}

	@PostMapping(path = "/count/insert", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public String testInsert(@RequestBody Counts req) {
		int value = req.getValue();
		
		boolean success = countService.insertValue(value);
		if(success) {
			return "Successfully inserted with a value of " + value;
		} else {
			return "Failed to insert a count with a value of " + value;
		}
	}
	
	@GetMapping("/count/{id}")
	public ResponseEntity<Counts> getCount(@PathVariable long id) {
		Counts response = countService.getCount(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}