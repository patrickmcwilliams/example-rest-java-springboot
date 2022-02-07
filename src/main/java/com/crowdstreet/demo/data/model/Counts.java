package com.crowdstreet.demo.data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Counts implements Serializable{
    
    @Id
    @GeneratedValue
    @Getter
    private long id;
    @Getter @Setter
    private int value;

    public Counts (int count) {
        this.value = count;
    }

    @JsonIgnore
    public String getAsJSON(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(this);
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
}
