package com.crowdstreet.demo.service;

import java.util.Optional;

import com.crowdstreet.demo.data.dao.StatusRepository;
import com.crowdstreet.demo.data.model.Status;
import com.crowdstreet.demo.data.model.Status.StatusTypes;
import com.crowdstreet.demo.exceptions.DAOException;
import com.crowdstreet.demo.data.model.CallBackRequest;

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
	
	public void postCallback(String type, long id) throws DAOException, Exception {
		if (!type.equals(StatusTypes.STARTED.toString())){
			throw new Exception("Need to use PUT for any status other than STARTED");
		}
		Optional<Status> response = statusRepository.findById(id);
		if (response.isPresent()){
			Status newStatus = response.get();
			newStatus.setStatus(StatusTypes.STARTED);
			statusRepository.save(newStatus);
		}
		else {
			throw new DAOException("Could not find record");
		}
	}

	public void putCallback(CallBackRequest status, long id) throws DAOException, Exception {
		if (status.getStatus().equals(StatusTypes.STARTED)){
			throw new Exception("Need to use POST for status STARTED");
		}
        Optional<Status> response = statusRepository.findById(id);
		if (response.isPresent()){
			Status newStatus = response.get();
			newStatus.setStatus(status.getStatus());
			newStatus.setDetail(status.getDetail());
			statusRepository.save(newStatus);
		}
		else {
			throw new DAOException("Could not find record");
		}
	}

	public Status getStatus(long id) throws DAOException, Exception {
        Optional<Status> response = statusRepository.findById(id);
		if (response.isPresent()){
			return response.get();
		}
		else {
			throw new DAOException("Could not find record");
		}
	}
}
