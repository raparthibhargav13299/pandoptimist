package com.stackroute.graphservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Id;



import org.springframework.data.neo4j.core.schema.Id;


@NoArgsConstructor
@AllArgsConstructor
@Data
@NodeEntity
public class Donor {

    @Id
     private int id;
    private String name;
    private String age;
    private String phoneNumber;
    private String emailId;
    private String city;
    private String bloodGroup;

}