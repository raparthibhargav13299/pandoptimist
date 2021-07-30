package com.stackroute.volunteer.exceptions;

public class VolunteerAlradyExistsException extends Exception{
    private String message;
    public VolunteerAlradyExistsException(String message) {
        super();
        this.message = message;
    }
    public VolunteerAlradyExistsException() {
    }
}