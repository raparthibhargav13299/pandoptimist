package com.stackroute.patientservice.exception;

public class RequestAlreadyExistsException extends Exception {
    private String message;

    public RequestAlreadyExistsException(){

    }
    public RequestAlreadyExistsException(String message){
        super();
        this.message=message;
    }
}
