package com.crowdstreet.demo.data.dao;

import com.crowdstreet.demo.data.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    
}
