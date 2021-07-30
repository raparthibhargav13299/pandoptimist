package com.stackroute.patientservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="plasmaRequest")
public class PlasmaRequest {
     @Id
    @Transient
    public static final String SEQUENCE_NAME="user_sequence";
     private String requestType="PlasmaRequest";
//     private String requestTime="myDateTime=LocalDateTime.now()";
    private int id;
    private String name;
    private int  age;
    private String bloodGroup;
    private String hospitalName;
    private String hospitalAddress;
    private String uploadPrescription;
    private String requestStatus="open";

}
