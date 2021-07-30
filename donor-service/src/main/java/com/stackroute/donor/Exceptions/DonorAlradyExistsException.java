package com.stackroute.donor.Exceptions;

public class DonorAlradyExistsException extends Exception{
    private String message;
    public DonorAlradyExistsException(String message) {
        super();
        this.message = message;
    }
    public DonorAlradyExistsException() {
    }
}