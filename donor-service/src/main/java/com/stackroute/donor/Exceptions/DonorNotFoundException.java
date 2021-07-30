package com.stackroute.donor.Exceptions;

public class DonorNotFoundException extends Exception{
    private String message;
    public DonorNotFoundException() {
    }
    public DonorNotFoundException(String message) {
        super();
        this.message = message;
    }
}