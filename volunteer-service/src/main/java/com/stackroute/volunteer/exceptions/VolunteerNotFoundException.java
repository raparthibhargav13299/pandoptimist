package com.stackroute.volunteer.exceptions;

public class VolunteerNotFoundException extends Exception{
    private String message;
    public VolunteerNotFoundException() {
    }
    public VolunteerNotFoundException(String message) {
        super();
        this.message = message;
    }
}