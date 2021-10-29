package com.crowdstreet.demo.Count;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crowdstreet.demo.Count.model.CreateCountRequest;
import com.crowdstreet.demo.Count.model.GetCountResponse;

@RestController
public class CountController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@PostMapping(path = "/count/insert", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public String testInsert(@RequestBody CreateCountRequest req) throws SQLException {
		int value = req.getValue();
		Connection co = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa",""); 
		String query = "insert into counts(value) values(" + value + ")";
		PreparedStatement st = co.prepareStatement(query);
		boolean rs = st.execute();
		
		return "Successfully inserted with a value of " + value;
	}
	
	@GetMapping("/count/{id}")
	public ResponseEntity<GetCountResponse> getCount(@PathVariable long id) throws SQLException {
		Connection co = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa",""); 
		String query = "select * from counts where id = "+ (int)id;
		PreparedStatement st = co.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		
		int value = -1;
		while(rs.next()) {
			value = rs.getInt("value");
		}
		
		GetCountResponse response= new GetCountResponse(value);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}