package com.crowdstreet.demo.data.dao;

import com.crowdstreet.demo.data.model.Counts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountsRepository extends JpaRepository<Counts, Long> {
    
}
