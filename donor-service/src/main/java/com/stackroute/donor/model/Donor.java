package com.stackroute.donor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "donor")
public class Donor {

    @Transient
    public static final String SEQUENCE_NAME="donor_sequence";
    @Id
    int id;
    private String name;
    private String age;
    private String phoneNumber;
    private String emailId;
    private String city;
    private String bloodGroup;

}