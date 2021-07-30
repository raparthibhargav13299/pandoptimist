package com.stackroute.volunteer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Transient;


@Document(collection = "volunteer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {

    @Transient
    public static final String SEQUENCE_NAME="volunteer_sequence";
    @Id
    private int id;
    private String name;
    private String phoneNumber;
    private String emailId;
    private String filepath;



}
