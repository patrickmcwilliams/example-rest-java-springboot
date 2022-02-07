package com.crowdstreet.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.crowdstreet.demo.exceptions.DAOException;
import com.crowdstreet.demo.exceptions.RequestException;

import com.crowdstreet.demo.data.model.CallBackRequest;
import com.crowdstreet.demo.data.model.Request;
import com.crowdstreet.demo.data.model.Status;
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

	@PutMapping(path="/callback/{id}",
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Void> putCallback(@RequestBody CallBackRequest status, @PathVariable long id) {
		try {
			statusService.putCallback(status, id);
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

	@GetMapping(path="/callback/{id}",
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> getStatus( @PathVariable long id) {
		try {
			Status status = statusService.getStatus(id);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
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

	@RequestMapping(value="**",method = RequestMethod.GET)
	public ResponseEntity<String> getCatchall(){
	return new ResponseEntity<String>("no thanks", HttpStatus.FORBIDDEN);
}
}