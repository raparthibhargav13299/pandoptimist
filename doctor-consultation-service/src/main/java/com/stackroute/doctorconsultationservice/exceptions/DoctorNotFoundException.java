package com.stackroute.doctorconsultationservice.exceptions;

public class DoctorNotFoundException extends Exception{
    private String message;
    public DoctorNotFoundException() {
    }
    public DoctorNotFoundException(String message) {
        super();
        this.message = message;
    }
}