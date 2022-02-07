package com.crowdstreet.demo.exceptions;

public class DAOException extends Exception{
    public DAOException(String errorMessage) {
        super(errorMessage);
    }
}
