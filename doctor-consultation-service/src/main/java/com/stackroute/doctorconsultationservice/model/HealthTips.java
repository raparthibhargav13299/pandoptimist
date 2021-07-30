package com.stackroute.doctorconsultationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "healthTips")
public class HealthTips {
    @Transient
    public static final String SEQUENCE_NAME="tips_sequence";
    @Id
    int id;
    String name;
    String specialist;
    String adviceOn;
    String tips;

}
