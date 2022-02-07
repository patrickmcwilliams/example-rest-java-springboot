package com.crowdstreet.demo.service;

import java.util.Optional;

import com.crowdstreet.demo.data.dao.StatusRepository;
import com.crowdstreet.demo.data.model.Status;
import com.crowdstreet.demo.exceptions.DAOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public long addStatus(Status status) throws DAOException {
		try {
			Status result = statusRepository.save(status);
			return result.getId();
		} catch (Exception e) {
			throw new DAOException("Error accessing database");
		}

	}
	
	public Status getCount(long id) {
        Optional<Status> response = statusRepository.findById(id);
		if (response.isPresent()){
			return response.get();
		}
		else {
			return new Status();
		}
	}
    
}
