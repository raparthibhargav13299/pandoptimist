package com.stackroute.patientservice.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import java.util.ArrayList;


@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="medicalRequest")
@Component

public class MedicalRequest {
    @Id
    @Transient
    public static final String SEQUENCE_NAME="user_sequence";
    private int id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    private String hospitalized;
    private String city;
    public ArrayList<Requirement> requirement;
    private String uploadPrescription;
    private String requestStatus;



}
