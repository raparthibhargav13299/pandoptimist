package com.stackroute.doctorconsultationservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "doctors")
public class Doctor {

    @Transient
public static final String SEQUENCE_NAME="doctor_sequence";
    @Id
    private int id;
    private String name;
    private String medicalRegistrationNumber;
    private String mobileNumber;
    private String email;
    private String image;
    private String healthTips;


    public Doctor(int id, String name, String medicalRegistrationNumber, String mobileNumber, String email, String image) {
        this.id = id;
        this.name = name;
        this.medicalRegistrationNumber = medicalRegistrationNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.image = image;
    }
}

