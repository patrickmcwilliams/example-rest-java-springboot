package com.crowdstreet.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.crowdstreet.demo.exceptions.DAOException;
import com.crowdstreet.demo.exceptions.RequestException;

import javax.servlet.http.HttpServletRequest;
import com.crowdstreet.demo.data.model.Request;
import com.crowdstreet.demo.data.model.Status.StatusTypes;
import com.crowdstreet.demo.service.StatusService;
import com.crowdstreet.demo.service.RequestService;

@RestController
public class APIController {
	
	@Autowired
	StatusService countService;
	@Autowired
	RequestService requestService;
	@Autowired
	StatusService statusService;


	@PostMapping(path="/request",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.TEXT_PLAIN_VALUE)
	public String postRequest(@RequestBody Request request) {
		long id;
		try {
			id = requestService.makeRequest(request);
		} 
		catch (RequestException e) {
			throw new ResponseStatusException(
			  HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		catch (Exception e){
			throw new ResponseStatusException(
				HttpStatus.BAD_GATEWAY, e.getMessage(), e);
		}
		return Long.toString(id);
	}

	@PostMapping(path="/callback/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Void> postCallback(@RequestBody String status, @PathVariable long id) {
		try {
			statusService.postCallback(status, id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} 
		catch (DAOException e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		catch (Exception e){
			throw new ResponseStatusException(
				HttpStatus.BAD_GATEWAY, e.getMessage(), e);
		}
	}

	// @RequestMapping(value = "")
	// public ResponseEntity<String> indexBase(final HttpServletRequest request){
	
	// 	return new ResponseEntity<String>("no thanks", HttpStatus.NOT_IMPLEMENTED);
	// }
	// @RequestMapping(value = "/**/{path:.*}")
	// public ResponseEntity<String> index(final HttpServletRequest request){
	
	// 	return indexBase(request);
	// }

	// @PostMapping(path = "/count/insert", 
	//         consumes = MediaType.APPLICATION_JSON_VALUE, 
	//         produces = MediaType.APPLICATION_JSON_VALUE)
	// public String testInsert(@RequestBody Status req) {
		
	// 	try {
	// 		long success = countService.addStatus(req);
	// 		return String.valueOf(success);
	// 	} catch (DAOException e) {
	// 		return e.getMessage();
	// 	}
	// }
	
	// @GetMapping("/count/{id}")
	// public ResponseEntity<Status> getCount(@PathVariable long id) {
	// 	Status response = countService.getCount(id);
	// 	return new ResponseEntity<>(response, HttpStatus.OK);
	// }

}