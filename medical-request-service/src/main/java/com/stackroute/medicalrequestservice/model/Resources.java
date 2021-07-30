package com.stackroute.medicalrequestservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "resource")
public class Resources {
    @Transient
    public static final String SEQUENCE_NAME="sequence";
    @Id
    private int id;
    private String createdBy;
    private String type;
    private String bedType="";
    private String medicineName="";
    private String typeofEquipment="";
    private String medicalEquipmentName="";
    private String city;
    private String avalabilityPlace;
    private String address;
    private String contactPersonName;
    private String contactMobileNumber;
    private String isVerified;
    private String verifiedBy;
    private Date AddedTime;
}
