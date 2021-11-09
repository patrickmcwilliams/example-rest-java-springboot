package com.crowdstreet.demo.Count;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crowdstreet.demo.Count.model.CreateCountRequest;
import com.crowdstreet.demo.Count.model.GetCountResponse;

@RestController
public class CountController {
	
	CountResource countResource = new CountResource();

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@PostMapping(path = "/count/insert", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public String testInsert(@RequestBody CreateCountRequest req) throws SQLException {
		int value = req.getValue();
		
		boolean success = countResource.insertValue(value);
		if(success) {
			return "Successfully inserted with a value of " + value;
		} else {
			return "Failed to insert a count with a value of " + value;
		}
	}
	
	@GetMapping("/count/{id}")
	public ResponseEntity<GetCountResponse> getCount(@PathVariable long id) throws SQLException {
		int value = countResource.getCount(id);
		
		GetCountResponse response= new GetCountResponse(value);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}