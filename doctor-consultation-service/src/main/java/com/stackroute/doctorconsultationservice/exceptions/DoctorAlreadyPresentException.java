package com.stackroute.doctorconsultationservice.exceptions;

public class DoctorAlreadyPresentException extends Exception{
    private String message;
    public DoctorAlreadyPresentException(String message) {
        super();
        this.message = message;
    }
    public DoctorAlreadyPresentException() {
    }
}
