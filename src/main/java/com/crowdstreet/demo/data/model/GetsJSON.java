package com.crowdstreet.demo.data.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class GetsJSON implements Serializable {
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
