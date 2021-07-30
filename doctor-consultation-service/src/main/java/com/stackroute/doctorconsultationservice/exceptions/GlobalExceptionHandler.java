package com.stackroute.doctorconsultationservice.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
        System.out.println(exception);
        return new ResponseEntity<>("Database connectivity is lost", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = DoctorAlreadyPresentException.class)
    public ResponseEntity<String> DoctorExistException(){
        return new ResponseEntity<>(message1, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = DoctorNotFoundException.class)
    public ResponseEntity<String> DoctorNotFoundException(){
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);
    }
}
