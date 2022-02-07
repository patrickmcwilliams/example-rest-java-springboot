package com.crowdstreet.demo.service;

import java.util.Optional;

import com.crowdstreet.demo.data.dao.CountsRepository;
import com.crowdstreet.demo.data.model.Counts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountService {

    @Autowired
    CountsRepository countRepository;

    public boolean insertValue(int value) {
        Counts count = new Counts(value);
        Counts result = countRepository.save(count);
		
		return result.getValue() == value;
	}
	
	public Counts getCount(long id) {
        Optional<Counts> response = countRepository.findById(id);
		if (response.isPresent()){
			return response.get();
		}
		else {
			Counts badCount = new Counts(-1);
			return badCount;
		}
	}
    
}
