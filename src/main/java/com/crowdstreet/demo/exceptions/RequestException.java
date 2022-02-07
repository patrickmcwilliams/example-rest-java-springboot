package com.crowdstreet.demo.exceptions;

public class RequestException extends Exception{
    public RequestException(String errorMessage) {
        super(errorMessage);
    }
}
