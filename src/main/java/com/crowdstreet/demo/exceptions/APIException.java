package com.crowdstreet.demo.exceptions;

public class APIException extends Exception{
    public APIException(String errorMessage) {
        super(errorMessage);
    }
}
